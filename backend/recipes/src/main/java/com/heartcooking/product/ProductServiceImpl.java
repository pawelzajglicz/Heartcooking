package com.heartcooking.product;

import com.heartcooking.exceptions.ProductNotFoundException;
import com.heartcooking.photo.Photo;
import com.heartcooking.photo.PhotoForProductDetailsDTO;
import com.heartcooking.product.dtos.ProductDetailsDTO;
import com.heartcooking.product.dtos.ProductForListDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Override
	public List<ProductForListDTO> getProducts() {

		var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		log.info(user.toString());

		return productRepository.findAll().stream().map(this::mapProductToProductForListDTO).toList();
	}

	@Override
	public ProductDetailsDTO getProductById(Long id) {
		return mapProductToProductDetailsDTO(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException()));
	}

	private ProductForListDTO mapProductToProductForListDTO(Product product) {

		return new ProductForListDTO(product.getId(),
									 product.getName(),
									 product.getDescription(), 
									 product.getPhotos().stream()
											 .filter(photo -> photo.getIsMain())
											 .findFirst()
											 .map(photo -> photo.getUrl()).orElse(null));
	}

	private ProductDetailsDTO mapProductToProductDetailsDTO(Product product) {

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
									 product.getIsAllergen(),
									 product.getIsVegan(),
									 product.getPhotos().stream()
											 .sorted(Comparator.comparing(Photo::getAddedAt))
											 .map(this::mapPhotoToPhotoForProductDetailsDTO)
											 .toList(),
									product.getTracesAllergens().stream().map(allergen -> allergen.getName()).toList());
	}

	private PhotoForProductDetailsDTO mapPhotoToPhotoForProductDetailsDTO(Photo photo) {
		return new PhotoForProductDetailsDTO(photo.getId(),
											 photo.getUrl(),
							 				 photo.getDescription(),
											 photo.getAddedAt(),
											 photo.getIsMain());
	}
}
