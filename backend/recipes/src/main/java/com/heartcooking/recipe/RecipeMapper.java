package com.heartcooking.recipe;

import com.heartcooking.Allergen;
import com.heartcooking.photo.Photo;
import com.heartcooking.photo.PhotoMapper;
import com.heartcooking.recipe.dtos.RecipeDetailsDTO;
import com.heartcooking.recipe.dtos.RecipeForListDTO;
import com.heartcooking.recipe.dtos.RecipeProductForRecipeDetailsDTO;
import com.heartcooking.recipe.dtos.RecipeStepForRecipeDetailsDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@AllArgsConstructor
@Service
public class RecipeMapper {

	private PhotoMapper photoMapper;

	public RecipeForListDTO mapRecipeToRecipeForListDTO(Recipe recipe) {
		return new RecipeForListDTO(recipe.getId(),
				recipe.getName(),
				recipe.getDescription(),
				recipe.getPhotos().stream()
						.filter(Photo::isMain)
						.findFirst()
						.map(Photo::getUrl).orElse(null));
	}

	RecipeDetailsDTO mapRecipeToRecipeDetailsDTO(Recipe recipe) {

		return new RecipeDetailsDTO(
				recipe.getId(),
				recipe.getName(),
				recipe.getDescription(),
				recipe.getCarbohydrates(),
				recipe.getFat(),
				recipe.getFiber(),
				recipe.getKilocalories(),
				recipe.getProtein(),
				recipe.getSalt(),
				recipe.getSaturatedFat(),
				recipe.getSugar(),
				recipe.getTotalWeight(),
				recipe.isAllergen(),
				recipe.isVegan(),
				recipe.getRecipeProducts().stream()
						.map(this::mapRecipeProductToRecipeProductForRecipeDetailsDTO)
						.toList(),
				recipe.getSteps().stream()
						.map(this::mapRecipeStepToRecipeStepForRecipeDetailsDTO)
						.toList(),
				recipe.getPhotos().stream()
						.sorted(Comparator.comparing(Photo::getAddedAt))
						.map(photoMapper::mapPhotoToPhotoForItemDetailsDTO)
						.toList(),
				recipe.getAllergens().stream().map(Allergen::getName).toList());
	}

	RecipeProductForRecipeDetailsDTO mapRecipeProductToRecipeProductForRecipeDetailsDTO(RecipeProduct recipeProduct) {
		return new RecipeProductForRecipeDetailsDTO(
				recipeProduct.getProduct().getId(),
				recipeProduct.getProduct().getName(),
				recipeProduct.getWeight(),
				recipeProduct.getProduct().getPhotos().stream()
						.filter(Photo::isMain)
						.findFirst()
						.map(Photo::getUrl).orElse(null)
		);
	}

	RecipeStepForRecipeDetailsDTO mapRecipeStepToRecipeStepForRecipeDetailsDTO(RecipeStep recipeStep) {
		return new RecipeStepForRecipeDetailsDTO(
				recipeStep.getOrderNumber(),
				recipeStep.getDescription(),
				recipeStep.getPhotos().stream()
						.filter(Photo::isMain)
						.findFirst()
						.map(Photo::getUrl).orElse(null)
		);
	}
}
