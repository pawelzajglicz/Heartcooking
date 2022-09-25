package com.heartcooking.photo;

import com.heartcooking.product.Product;
import com.heartcooking.recipe.Recipe;
import com.heartcooking.recipe.RecipeStep;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@Builder
@Getter
@Entity
@NoArgsConstructor
@Setter
@Table(name = "photos", schema = "heartcooking")
public class Photo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column()
	private String url;

	@Column()
	private String description;

	@Column(name = "added_at")
	private Instant addedAt;

	@Column(name = "is_main")
	@Getter(AccessLevel.NONE)
	private Boolean isMain;

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name="recipe_id")
	private Recipe recipe;

	@ManyToOne
	@JoinColumn(name="recipe_step_id")
	private RecipeStep recipeStep;

	public Boolean isMain() {
		return isMain;
	}
}