package com.faik.Jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest { // Burada koruma olsun diye Ek olarak burayı açtık yan

	private String refreshToken;
}
