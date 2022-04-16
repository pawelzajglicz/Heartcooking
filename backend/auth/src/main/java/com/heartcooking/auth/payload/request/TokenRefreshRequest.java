package com.heartcooking.auth.payload.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRefreshRequest {

	@NotBlank
	private String refreshToken;
}