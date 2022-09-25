package com.heartcooking.product;

import com.heartcooking.photo.NewPhotoDTO;
import com.heartcooking.photo.Photo;
import com.heartcooking.photo.PhotoForItemDetailsDTO;
import com.heartcooking.photo.PhotoMapper;
import com.heartcooking.product.dtos.NewProductDTO;
import com.heartcooking.product.dtos.ProductDetailsDTO;
import com.heartcooking.product.dtos.ProductForListDTO;
import com.heartcooking.tracesallergen.TracesAllergen;
import com.heartcooking.tracesallergen.TracesAllergenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductMapper {

	private PhotoMapper photoMapper;
	private TracesAllergenRepository tracesAllergenRepository;

	ProductForListDTO mapProductToProductForListDTO(Product product) {

		return new ProductForListDTO(product.getId(),
				product.getName(),
				product.getDescription(),
				product.getPhotos().stream()
						.filter(Photo::isMain)
						.findFirst()
						.map(Photo::getUrl).orElse(null));
	}

	ProductDetailsDTO mapProductToProductDetailsDTO(Product product) {

		return new ProductDetailsDTO(product.getId(),
				product.getName(),
				product.getDescription(),
				product.getCarbohydrates(),
				product.getFat(),
				product.getFiber(),
				product.getKilocalories(),
				product.getProtein(),
				product.getSalt(),
				product.getSaturatedFat(),
				product.getSugar(),
				product.isAllergen(),
				product.isVegan(),
				product.getPhotos().stream()
						.sorted(Comparator.comparing(Photo::getAddedAt))
						.map(photoMapper::mapPhotoToPhotoForItemDetailsDTO)
						.toList(),
				product.getTracesAllergens().stream().map(TracesAllergen::getName).toList());
	}

	public Product mapNewProductDTOToProductForSave(NewProductDTO newProductDto) {
		Product product = Product.builder()
				.name(newProductDto.getName())
				.description(newProductDto.getDescription())
				.carbohydrates(newProductDto.getCarbohydrates())
				.fat(newProductDto.getFat())
				.fiber(newProductDto.getFiber())
				.kilocalories(newProductDto.getKilocalories())
				.protein(newProductDto.getProtein())
				.salt(newProductDto.getSalt())
				.saturatedFat(newProductDto.getSaturatedFat())
				.sugar(newProductDto.getSugar())
				.isAllergen(newProductDto.getIsAllergen())
				.isVegan(newProductDto.getIsVegan())
				.build();

		if (newProductDto.getPhotos() != null && newProductDto.getPhotos().size() > 0) {
			product.setPhotos(newProductDto.getPhotos().stream()
					.map(photo -> mapNewPhotoDTOToPhoto(photo, product))
					.collect(Collectors.toSet()));
		}

		if (newProductDto.getTracesAllergens() != null) {
			product.setTracesAllergens(newProductDto.getTracesAllergens());
		}

		return product;
	}

	private Photo mapNewPhotoDTOToPhoto(NewPhotoDTO newPhotoDTO, Product newProduct) {
		return Photo.builder()
				.url(newPhotoDTO.getUrl())
				.description(newPhotoDTO.getDescription())
				.addedAt(newPhotoDTO.getAddedAt())
				.isMain(newPhotoDTO.isMain())
				.product(newProduct)
				.build();
	}
}
