package com.faik.Jwt;

import java.awt.RenderingHints.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	public  static final  String SECRET_KEY = "7hnNctRB4sX3GOLGIlTr1zI7GVV/nPDbb8X9RARRFq8="; 

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claimsMap = new HashMap<>();
		claimsMap.put("Role", "Admin");
		
		return  Jwts.builder().
		setSubject(userDetails.getUsername()).
		addClaims(claimsMap). // Strinng ve obejct bir değer istiyor
		setIssuedAt(new Date()).
		setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2)). // 2 saat yapar milisaniye cinsinden
		signWith((java.security.Key) getKey(), io.jsonwebtoken.SignatureAlgorithm.HS256).
		compact();
	}
	
	public Object getClaimsBykey(String token, String key) {
		Claims claims = getClaims(token);
		return  claims.get(key);
		}
	
	public Claims getClaims(String token) { // Anahtar vereceğiz
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKeyResolver((SigningKeyResolver) getKey())
				.build()
				.parseClaimsJws(token).getBody();
		
		return claims;
	}
	
	
	
	public <T> T exportToken(String token, Function<Claims , T> claimsfunction) {
			
		Claims claims =  getClaims(token);
		
		return claimsfunction.apply(claims);
	}
	
	public String getUsernameByToken(String token) {
		return exportToken(token, Claims::getSubject); // claims objesi üzerinden getSubject metodunu çalıştır demek
	}
	
	public boolean isTokenexperied(String token) {
		Date experiedDate = exportToken(token, Claims::getExpiration);
		return new Date().before(experiedDate);
	}
	
	public Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return (Key) Keys.hmacShaKeyFor(keyBytes);
	}
}
