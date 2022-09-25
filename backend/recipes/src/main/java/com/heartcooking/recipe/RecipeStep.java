package com.heartcooking.recipe;

import com.heartcooking.photo.Photo;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "recipe_steps", schema = "heartcooking")
public class RecipeStep {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "order_number")
	private Integer orderNumber;

	@Column()
	private String description;

	@ManyToOne
	@JoinColumn(name="recipe_id")
	private Recipe recipe;

	@OneToMany(mappedBy = "recipeStep", cascade = CascadeType.ALL)
	private Set<Photo> photos;
}
