package com.heartcooking.product;

import com.heartcooking.product.dtos.ProductForListDto;
import com.heartcooking.photo.Photo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Override
	public List<ProductForListDto> getProducts() {

		var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		log.info(user.toString());

		return productRepository.findAll().stream().map(this::mapProductToProductForListDTO).toList();
	}

	private ProductForListDto mapProductToProductForListDTO(Product product) {

		return new ProductForListDto(product.getId(),
									 product.getName(),
									 product.getDescription(), 
									 product.getPhotos().stream()
											 .filter(photo -> photo.getIsMain())
											 .findFirst()
											 .map(photo -> photo.getUrl()).orElse(null));
	}
}
