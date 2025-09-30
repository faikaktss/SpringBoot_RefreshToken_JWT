package com.faik.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faik.Model.ResfreshToken;

public interface RefreshTokenRepository  extends JpaRepository<ResfreshToken ,Long>{
	Optional<RefreshTokenRepository> findByRefreshToken(String refreshToken);
}
