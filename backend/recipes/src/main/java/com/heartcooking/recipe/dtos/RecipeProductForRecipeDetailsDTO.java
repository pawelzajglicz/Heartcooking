package com.heartcooking.recipe.dtos;

public record RecipeProductForRecipeDetailsDTO(Long id,
											   String name,
											   Double weight,
											   String photoUrl) {
}
