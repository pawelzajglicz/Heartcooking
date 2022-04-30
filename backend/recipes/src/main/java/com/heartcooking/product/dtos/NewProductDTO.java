package com.heartcooking.product.dtos;

import com.heartcooking.photo.NewPhotoDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class NewProductDTO {

	@NotBlank
	String name;

	@NotBlank
	String description;

	@NotNull
	Double carbohydrates;

	@NotNull
	Double fat;

	Double fiber;

	@NotNull
	Double kilocalories;

	@NotNull
	Double protein;

	Double salt;

	@NotNull
	Double saturatedFat;

	@NotNull
	Double sugar;

	@NotNull
	Boolean isAllergen;

	@NotNull
	Boolean isVegan;

	List<NewPhotoDTO> photos;

	List<Long> tracesAllergensIds;
}
