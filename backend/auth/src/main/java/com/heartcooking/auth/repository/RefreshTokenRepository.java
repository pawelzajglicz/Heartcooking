package com.heartcooking.auth.repository;

import com.heartcooking.auth.models.RefreshToken;
import com.heartcooking.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	@Override
	Optional<RefreshToken> findById(Long id);

	Optional<RefreshToken> findByToken(String token);

	int deleteByUser(User user);
}
