package com.heartcooking.recipe;


import com.heartcooking.recipe.dtos.RecipeDetailsDTO;
import com.heartcooking.recipe.dtos.RecipeForListDTO;

import java.util.List;

public interface RecipeService {

	List<RecipeForListDTO> getRecipes();

	RecipeDetailsDTO getRecipeById(Long id);
}
