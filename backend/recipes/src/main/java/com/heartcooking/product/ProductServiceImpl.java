package com.heartcooking.product;

import com.heartcooking.exceptions.ProductNotFoundException;
import com.heartcooking.product.dtos.NewProductDTO;
import com.heartcooking.product.dtos.ProductDetailsDTO;
import com.heartcooking.product.dtos.ProductForListDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	private ProductMapper productMapper;
	private ProductRepository productRepository;

	@Override
	public void add(NewProductDTO newProductDto) {
		var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("Start adding product {} by user {}", newProductDto.getName(), user.toString());

		Product newProduct = productMapper.mapNewProductDTOToProductForSave(newProductDto);
		productRepository.saveAndFlush(newProduct);

		log.info("Finish adding product {} by user {}", newProduct.getName(), user.toString());
	}

	@Override
	public List<ProductForListDTO> getProducts() {

		var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		log.info(user.toString());

		return productRepository.findAll().stream().map(productMapper::mapProductToProductForListDTO).toList();
	}

	@Override
	public ProductDetailsDTO getProductById(Long id) {
		return productMapper.mapProductToProductDetailsDTO(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException()));
	}
}
