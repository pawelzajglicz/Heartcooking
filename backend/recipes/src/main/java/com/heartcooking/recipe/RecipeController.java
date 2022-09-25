package com.heartcooking.recipe;

import com.heartcooking.auth.security.services.UserDetailsImpl;
import com.heartcooking.product.dtos.ProductDetailsDTO;
import com.heartcooking.recipe.dtos.RecipeDetailsDTO;
import com.heartcooking.recipe.dtos.RecipeForListDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recipes")
@Slf4j
public class RecipeController {

	private RecipeService recipeService;

	@GetMapping(path = {"", "/"})
	public ResponseEntity<List<RecipeForListDTO>> getRecipes() {

		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("There was attempt to get recipes by user with id: {}", userDetails.getId());

		return ResponseEntity.ok(recipeService.getRecipes());
	}

	@GetMapping("/{recipeId}")
	public ResponseEntity<RecipeDetailsDTO> getRecipeById(@PathVariable Long recipeId) {

		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("There was attempt to get recipe with id {} by user with id: {}", recipeId, userDetails.getId());

		return ResponseEntity.ok(recipeService.getRecipeById(recipeId));
	}
}
