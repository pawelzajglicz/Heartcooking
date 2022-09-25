package com.heartcooking.recipe;

import com.heartcooking.exceptions.RecipeNotFoundException;
import com.heartcooking.recipe.dtos.RecipeDetailsDTO;
import com.heartcooking.recipe.dtos.RecipeForListDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

	private RecipeMapper recipeMapper;
	private RecipeRepository recipeRepository;

	@Override
	public List<RecipeForListDTO> getRecipes() {

		return recipeRepository.findAll().stream().map(recipeMapper::mapRecipeToRecipeForListDTO).toList();
	}

	@Override
	public RecipeDetailsDTO getRecipeById(Long id) {
		return recipeMapper.mapRecipeToRecipeDetailsDTO(recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new));
	}
}
