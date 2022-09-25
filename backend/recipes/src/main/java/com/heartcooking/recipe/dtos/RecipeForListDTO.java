package com.heartcooking.recipe.dtos;

public record RecipeForListDTO(Long id,
							   String name,
							   String description,
							   String photoUrl) {}