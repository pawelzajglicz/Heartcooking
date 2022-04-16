package com.heartcooking.product;

import com.heartcooking.product.dtos.ProductForListDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProductService productsService;

	@GetMapping("")
	public ResponseEntity<List<ProductForListDto>> getProducts() {

		return ResponseEntity.ok(productsService.getProducts());
	}
}
