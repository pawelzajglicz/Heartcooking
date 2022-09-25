package com.heartcooking.recipe.dtos;

import com.heartcooking.photo.PhotoForItemDetailsDTO;

import java.util.List;

public record RecipeDetailsDTO(Long id,
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
							   Double totalWeight,
							   Boolean isAllergen,
							   Boolean isVegan,
							   List<RecipeProductForRecipeDetailsDTO> recipeProducts,
							   List<RecipeStepForRecipeDetailsDTO> recipeSteps,
							   List<PhotoForItemDetailsDTO> photos,
							   List<String> allergensNames) {}