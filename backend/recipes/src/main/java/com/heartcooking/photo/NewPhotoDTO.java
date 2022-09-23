package com.heartcooking.photo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
public class NewPhotoDTO {

	@NotBlank
	String url;

	@NotBlank
	String description;

	private Instant addedAt = Instant.now();

	@JsonProperty("isMain")
	boolean isMain;

}
