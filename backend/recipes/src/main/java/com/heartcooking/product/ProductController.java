package com.heartcooking.product;

import com.heartcooking.product.dtos.NewProductDTO;
import com.heartcooking.product.dtos.ProductDetailsDTO;
import com.heartcooking.product.dtos.ProductForListDTO;
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

	@GetMapping(path = {"", "/"})
	public ResponseEntity<List<ProductForListDTO>> getProducts() {

		return ResponseEntity.ok(productsService.getProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDetailsDTO> getProductById(@PathVariable Long id) {

		return ResponseEntity.ok(productsService.getProductById(id));
	}

	@PostMapping(path = {"", "/"})
	public ResponseEntity<Void> addProduct(@RequestBody NewProductDTO newProduct) {

		productsService.add(newProduct);

		return ResponseEntity.ok().build();
	}
}
