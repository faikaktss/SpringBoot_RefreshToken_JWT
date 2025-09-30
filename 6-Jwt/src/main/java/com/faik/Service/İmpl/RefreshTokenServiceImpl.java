package com.faik.Service.İmpl;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Service;

import com.faik.Jwt.AuthResponse;
import com.faik.Jwt.JwtService;
import com.faik.Jwt.RefreshTokenRequest;
import com.faik.Model.ResfreshToken;
import com.faik.Model.User;
import com.faik.Repository.RefreshTokenRepository;
import com.faik.Service.IRefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService{

    private final AuthenticationProvider authenticationProvider;

	@Autowired
	private RefreshTokenRepository resRefreshTokenRepository;

	@Autowired
	private JwtService jwtService;
	
    RefreshTokenServiceImpl(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }
	
	public boolean isrefreshTokenExpired(Date expiredDate) {
		return new Date(0).before(expiredDate);  // Token'ın süresinin dolup dolmadığına bakar
	}
	
	private ResfreshToken createResfreshToken(User user) {
		ResfreshToken resfreshToken = new ResfreshToken();
		resfreshToken.setUser(user);
		resfreshToken.setRefreshToken(UUID.randomUUID().toString());
		resfreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
		
		return resfreshToken;
	}
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
			  Optional<RefreshTokenRepository>  optional = resRefreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		
			  if(optional.isEmpty()) {
				  System.out.println("Geçeriz RefreshToken girişi yaptınız" + request.getRefreshToken());
			  }
			  
			  ResfreshToken resfreshToken = (ResfreshToken) optional.get();
			  if(!isrefreshTokenExpired(resfreshToken.getExpiredDate())) {
				  System.out.println("Girdigin refreshToken'ın süresi dolmuştur" + request.getRefreshToken());
			  }
			  
			   String accessToken= jwtService.generateToken(resfreshToken.getUser()); // Burada refreshtoken sayesinde access token'ı da yeniliyorum
			   ResfreshToken saveResfreshToken = resRefreshTokenRepository.save(createResfreshToken(resfreshToken.getUser()));
			   
		return new AuthResponse(accessToken, saveResfreshToken.getRefreshToken());
	}

}
