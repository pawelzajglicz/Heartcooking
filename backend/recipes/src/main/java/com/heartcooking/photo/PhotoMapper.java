package com.heartcooking.photo;

import org.springframework.stereotype.Service;

@Service
public class PhotoMapper {

	public PhotoForItemDetailsDTO mapPhotoToPhotoForItemDetailsDTO(Photo photo) {
		return new PhotoForItemDetailsDTO(photo.getId(),
				photo.getUrl(),
				photo.getDescription(),
				photo.getAddedAt(),
				photo.isMain());
	}
}
