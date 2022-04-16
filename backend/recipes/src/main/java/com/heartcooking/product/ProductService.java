package com.heartcooking.product;

import com.heartcooking.product.dtos.ProductForListDto;

import java.util.List;

public interface ProductService {

	List<ProductForListDto> getProducts();
}
