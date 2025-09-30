package com.faik.Controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.faik.Controller.IRestAuthController;
import com.faik.Dto.DtoUser;
import com.faik.Jwt.AuthRequest;
import com.faik.Jwt.AuthResponse;
import com.faik.Jwt.RefreshTokenRequest;
import com.faik.Service.IAuthService;
import com.faik.Service.IRefreshTokenService;

import jakarta.validation.Valid;

@RestController
public class RestAuthControllerImpl implements IRestAuthController{

	@Autowired
	private IAuthService authService;
	
	@Autowired
	private IRefreshTokenService  refreshTokenService;
	
	
	@PostMapping("/register")
	@Override
	public DtoUser register(@Valid @RequestBody AuthRequest authRequest) { // @Valid ile notempty anatasyonlarını aktif hale getirmemiz lazım
		return authService.register(authRequest);
	}

	@PostMapping("/authenticate")
	@Override
	public AuthResponse authenticate(@Valid @RequestBody AuthRequest authRequest) {
		
		return authService.authenticate(authRequest);
	}

	@PostMapping("/refreshToken")
	@Override
	public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {
		return refreshTokenService.refreshToken(request);
	}

}

