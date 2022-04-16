package com.heartcooking.auth.security.services;

import com.heartcooking.auth.exception.TokenRefreshException;
import com.heartcooking.auth.models.RefreshToken;
import com.heartcooking.auth.payload.request.TokenRefreshRequest;
import com.heartcooking.auth.payload.response.TokenRefreshResponse;
import com.heartcooking.auth.repository.RefreshTokenRepository;
import com.heartcooking.auth.repository.UserRepository;
import com.heartcooking.auth.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	private final JwtUtils jwtUtils;
	private final RefreshTokenRepository refreshTokenRepository;
	private final UserRepository userRepository;
	@Value("${jwt.refreshExpirationMs}")
	private Long refreshTokenDurationMs;

	public RefreshTokenServiceImpl(JwtUtils jwtUtils, RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
		this.jwtUtils = jwtUtils;
		this.refreshTokenRepository = refreshTokenRepository;
		this.userRepository = userRepository;
	}

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(Long userId) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(userRepository.findById(userId).get());
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

	public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) {

		String requestRefreshToken = tokenRefreshRequest.getRefreshToken();
		return findByToken(requestRefreshToken)
				.map(this::verifyExpiration)
				.map(RefreshToken::getUser)
				.map(user -> {
					String token = jwtUtils.generateTokenFromUsername(user.getUsername());
					return new TokenRefreshResponse(token, requestRefreshToken);
				})
				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
						"Refresh token is not in database!"));
	}

	@Transactional
	public int deleteByUserId(Long userId) {
		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
	}
}
