package com.faik.Service;

import com.faik.Dto.DtoUser;
import com.faik.Jwt.AuthRequest;
import com.faik.Jwt.AuthResponse;

public interface IAuthService {
	public DtoUser register(AuthRequest authRequest);
	
	public AuthResponse authenticate(AuthRequest authRequest);
}
