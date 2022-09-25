package com.heartcooking.recipe;

import com.heartcooking.Allergen;
import com.heartcooking.auth.models.User;
import com.heartcooking.photo.Photo;
import com.heartcooking.product.Product;
import com.heartcooking.tracesallergen.TracesAllergen;
import lombok.*;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Instant;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "recipes", schema = "heartcooking")
public class Recipe {

	private static DecimalFormat decimalFormat;
	static {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		decimalFormat = new DecimalFormat("####0.00", symbols);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column()
	private String name;

	@Column()
	private String description;

	@Column(name = "added_at")
	private Instant addedAt;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private User author;

	@OneToMany(mappedBy = "recipe",
			cascade = CascadeType.MERGE)
	Set<RecipeProduct> recipeProducts;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private Set<RecipeStep> steps;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.MERGE)
	private Set<Photo> photos;

	@Transient
	private Double totalWeight;

	public Double getTotalWeight() {
		if (totalWeight != null) {
			return totalWeight;
		}

		totalWeight = recipeProducts.stream()
				.mapToDouble(RecipeProduct::getWeight)
				.sum();

		return totalWeight;
	}

	public Double getCarbohydrates() {
		Double sum = recipeProducts.stream()
				.mapToDouble(recipeProducts -> recipeProducts.getWeight() * recipeProducts.getProduct().getCarbohydrates())
				.map(totalCarbohydrates -> totalCarbohydrates / getTotalWeight())
				.sum();

		return Double.parseDouble(decimalFormat.format(sum));
	}

	public Double getFat() {
		Double sum = recipeProducts.stream()
				.mapToDouble(recipeProducts -> recipeProducts.getWeight() * recipeProducts.getProduct().getFat())
				.map(totalFat -> totalFat / getTotalWeight())
				.sum();

		return Double.parseDouble(decimalFormat.format(sum));
	}

	public Double getFiber() {
		Double sum = recipeProducts.stream()
				.mapToDouble(recipeProducts -> recipeProducts.getWeight() * recipeProducts.getProduct().getFiber())
				.map(totalFiber -> totalFiber / getTotalWeight())
				.sum();

		return Double.parseDouble(decimalFormat.format(sum));
	}

	public Double getKilocalories() {
		Double sum = recipeProducts.stream()
				.mapToDouble(recipeProducts -> recipeProducts.getWeight() * recipeProducts.getProduct().getKilocalories())
				.map(totalKilocalories -> totalKilocalories / getTotalWeight())
				.sum();

		return Double.parseDouble(decimalFormat.format(sum));
	}

	public Double getProtein() {
		Double sum = recipeProducts.stream()
				.mapToDouble(recipeProducts -> recipeProducts.getWeight() * recipeProducts.getProduct().getProtein())
				.map(totalProtein -> totalProtein / getTotalWeight())
				.sum();

		return Double.parseDouble(decimalFormat.format(sum));
	}

	public Double getSalt() {

		if (recipeProducts.stream()
				.map(RecipeProduct::getProduct)
				.anyMatch(product -> product.getSalt() == null || product.getSalt().isNaN())) {
			return null;
		}

		Double sum = recipeProducts.stream()
				.mapToDouble(recipeProducts -> recipeProducts.getWeight() * recipeProducts.getProduct().getSalt())
				.map(totalSalt -> totalSalt / getTotalWeight())
				.sum();

		return Double.parseDouble(decimalFormat.format(sum));
	}

	public Double getSaturatedFat() {
		Double sum = recipeProducts.stream()
				.mapToDouble(recipeProducts -> recipeProducts.getWeight() * recipeProducts.getProduct().getSaturatedFat())
				.map(totalSaturatedFat -> totalSaturatedFat / getTotalWeight())
				.sum();

		return Double.parseDouble(decimalFormat.format(sum));
	}

	public Double getSugar() {
		Double sum = recipeProducts.stream()
				.mapToDouble(recipeProducts -> recipeProducts.getWeight() * recipeProducts.getProduct().getSugar())
				.map(totalSugar -> totalSugar / getTotalWeight())
				.sum();

		return Double.parseDouble(decimalFormat.format(sum));
	}

	public Boolean isAllergen() {
		return recipeProducts.stream().anyMatch(recipeProduct -> recipeProduct.getProduct().isAllergen());
	}

	public Boolean isVegan() {
		return recipeProducts.stream().allMatch(recipeProduct -> recipeProduct.getProduct().isVegan());
	}

	public Set<Allergen> getAllergens() {
		Set<TracesAllergen> tracesAllergens = recipeProducts.stream()
				.flatMap(recipeProduct -> recipeProduct.getProduct().getTracesAllergens().stream())
				.collect(Collectors.toSet());

		Set<Product> allergenProducts = recipeProducts.stream()
				.map(RecipeProduct::getProduct)
				.filter(Product::isAllergen)
				.collect(Collectors.toSet());

		return Stream.concat(tracesAllergens.stream(), allergenProducts.stream())
				.collect(Collectors.toSet());
	}
}
