package com.DailyPract.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DailyPract.DTO.ProductT1Request;
import com.DailyPract.DTO.ProductT1Response;
import com.DailyPract.DTO.UpdateProductRequest;
import com.DailyPract.Enum.ProductCategory;
import com.DailyPract.Service.ProductT1Service;
import com.DailyPract.Util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductT1Controller {

	private final ProductT1Service service;
	
	@PostMapping
	public ResponseEntity<ApiResponse<ProductT1Response>> createProduct(@Valid @RequestBody ProductT1Request request)
	{
		ProductT1Response product=service.createProduct(request);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.onSuccess("Product created successfully", product));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductT1Response>> getProduct(@PathVariable long id){
		
		ProductT1Response product=service.getProduct(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.onSuccess("Product retrieved successfully", product));
		
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<ProductT1Response>>> getProductsByCategory(@RequestParam(required = false) ProductCategory category){
		List<ProductT1Response> products=service.getProducts(category);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.onSuccess("Products retrieved successfully", products));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductT1Response>> updateProduct(@PathVariable long id, @Valid @RequestBody UpdateProductRequest updRequest){
		
		ProductT1Response updProd=service.updateProduct(id, updRequest);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.onSuccess("Product updated successfully", updProd));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable long id){
		
		service.deleteProduct(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.onSuccess("Product deleted successfully", null));
		
	}
	
	
	
	
	
	
}
