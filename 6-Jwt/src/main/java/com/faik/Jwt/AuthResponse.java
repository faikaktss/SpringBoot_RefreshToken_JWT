package com.faik.Jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	/*
	 * Burada normal olarak ilk başta token döndürüyorduk
	 * ama ek olarak şimdi refreshtoken'da geldiği için iki token olması lazım
	 * access token ve refresh token
	 * access token kısa süreli bir tokendır
	 * refresh token ise access token'dan uzundur 
	 * ve access token bittiğinde ise refresh token ile bunları yenileyebiliriz
	 * */
	private String accessToken;
	
	private String refreshToken;
}
