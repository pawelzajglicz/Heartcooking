package com.heartcooking.product;

import com.heartcooking.product.dtos.NewProductDTO;
import com.heartcooking.product.dtos.ProductDetailsDTO;
import com.heartcooking.product.dtos.ProductForListDTO;

import java.util.List;

public interface ProductService {

	void add(NewProductDTO newProduct);

	List<ProductForListDTO> getProducts();

	ProductDetailsDTO getProductById(Long id);
}
