package com.faik.Controller;

import com.faik.Dto.DtoUser;
import com.faik.Jwt.AuthRequest;
import com.faik.Jwt.AuthResponse;
import com.faik.Jwt.RefreshTokenRequest;

public interface IRestAuthController {

	public DtoUser register(AuthRequest authRequest);

	public AuthResponse authenticate(AuthRequest authRequest);
	
	public AuthResponse refreshToken(RefreshTokenRequest request);
}	


