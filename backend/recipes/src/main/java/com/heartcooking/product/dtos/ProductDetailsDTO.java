package com.heartcooking.product.dtos;

import com.heartcooking.photo.PhotoForProductDetailsDTO;

import java.util.List;

public record ProductDetailsDTO(Long id,
								String name,
								String description,
								Double carbohydrates,
								Double fat,
								Double fiber,
								Double kilocalories,
								Double protein,
								Double salt,
								Double saturatedFat,
								Double sugar,
								Boolean isAllergen,
								Boolean isVegan,
								List<PhotoForProductDetailsDTO> photoUrls,
								List<String> allergensNames) {}