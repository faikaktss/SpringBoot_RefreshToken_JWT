package com.faik.Service.İmpl;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service; 
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.faik.Dto.DtoUser;
import com.faik.Jwt.AuthRequest;
import com.faik.Jwt.AuthResponse;
import com.faik.Jwt.JwtService;
import com.faik.Model.ResfreshToken;
import com.faik.Model.User;
import com.faik.Repository.RefreshTokenRepository;
import com.faik.Repository.UserRepository;
import com.faik.Service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	private ResfreshToken createResfreshToken( User user) {
		
		 // Id oto olarak artacak
		ResfreshToken  refreshToken  = new ResfreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
		refreshToken.setUser(user);
		
		return refreshToken;
	}
	
	@Override
	public DtoUser register(AuthRequest authRequest) {
		
		DtoUser dto = new DtoUser();
		User user = new User();
		
		user.setUsername(authRequest.getUsername());
		user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
		
		
		User savedUser = userRepository.save(user);
		BeanUtils.copyProperties(savedUser, dto);
		return dto;
	}

	@Override
	public AuthResponse authenticate(AuthRequest authRequest) {
		try {
		
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
			authenticationProvider.authenticate(authenticationToken); // Kimlik kontrolü yapılıyor
			
			Optional<User> optionalUser =  userRepository.findByUsername(authRequest.getUsername());
			String accessToken = jwtService.generateToken(optionalUser.get()); // Kullanıcı buradaki token'ı sonradan geri döndürüyor 
			
			ResfreshToken resfreshToken = createResfreshToken(optionalUser.get());
			refreshTokenRepository.save(resfreshToken); // refresh token'ı veritabanına kaydettiim
			
			return new AuthResponse(accessToken, resfreshToken.getRefreshToken());
			
		} catch (Exception e) {
			System.out.println("Kullanıcı adı veya şifre hatalı");
		}
		
		
		return null;
	}

}
