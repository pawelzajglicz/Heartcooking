package com.heartcooking.auth.dto;

import com.heartcooking.auth.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SimpleUserInfo {

	Long id;
	String username;

	public static SimpleUserInfo userToSimpleUserInfo(User user) {
		return SimpleUserInfo.builder()
				.id(user.getId())
				.username(user.getUsername())
				.build();
	}
}
