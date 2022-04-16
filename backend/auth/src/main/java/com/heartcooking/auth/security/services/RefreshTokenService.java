package com.heartcooking.auth.security.services;

import com.heartcooking.auth.models.RefreshToken;
import com.heartcooking.auth.payload.request.TokenRefreshRequest;
import com.heartcooking.auth.payload.response.TokenRefreshResponse;

import java.util.Optional;

public interface RefreshTokenService {
	RefreshToken createRefreshToken(Long userId);

	Optional<RefreshToken> findByToken(String token);

	TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest);

	RefreshToken verifyExpiration(RefreshToken token);
}
