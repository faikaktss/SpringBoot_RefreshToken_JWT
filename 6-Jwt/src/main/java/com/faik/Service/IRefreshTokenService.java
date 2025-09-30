package com.faik.Service;

import com.faik.Jwt.AuthResponse;
import com.faik.Jwt.RefreshTokenRequest;

public interface IRefreshTokenService {
	 // AuthRespone bize hem access token hem de refresh token ile birlikte döner
	public AuthResponse refreshToken(RefreshTokenRequest request);
	
}
