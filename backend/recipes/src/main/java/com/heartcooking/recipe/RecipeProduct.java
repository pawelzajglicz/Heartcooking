package com.heartcooking.recipe;

import com.heartcooking.product.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "RecipeProduct")
@Getter
@Setter
@Table(name = "recipes_products")
public class RecipeProduct {

	@EmbeddedId
	private RecipeProductId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("productId")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("recipeId")
	private Recipe recipe;

	@Column(name = "weight")
	private Double weight;

	private RecipeProduct() {
	}

	private RecipeProduct(Product product, Recipe recipe) {
		this.product = product;
		this.recipe = recipe;
		this.id = new RecipeProductId(product.getId(), recipe.getId());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass())
			return false;

		RecipeProduct that = (RecipeProduct) o;
		return Objects.equals(product, that.product) &&
				Objects.equals(recipe, that.recipe);
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, recipe);
	}
}
