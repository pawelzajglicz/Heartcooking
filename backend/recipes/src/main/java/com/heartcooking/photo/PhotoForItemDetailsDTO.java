package com.heartcooking.photo;

import java.time.Instant;

public record PhotoForItemDetailsDTO(Long id,
									 String url,
									 String description,
									 Instant addedAt,
									 boolean isMain) {
}
