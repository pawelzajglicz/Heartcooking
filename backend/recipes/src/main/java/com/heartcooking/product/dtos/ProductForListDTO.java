package com.heartcooking.product.dtos;

public record ProductForListDTO(Long id,
								String name,
								String description,
								String photoUrl) {}