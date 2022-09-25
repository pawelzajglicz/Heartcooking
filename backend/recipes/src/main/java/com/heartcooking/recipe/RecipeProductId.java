package com.heartcooking.recipe;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class RecipeProductId implements Serializable {

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "recipe_id")
	private Long recipeId;
}
