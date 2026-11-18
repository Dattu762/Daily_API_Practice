package com.DailyPract.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.DailyPract.DTO.ProductT1Request;
import com.DailyPract.DTO.ProductT1Response;
import com.DailyPract.DTO.UpdateProductRequest;
import com.DailyPract.Enum.ProductCategory;


public interface ProductT1Service {

	ProductT1Response createProduct(ProductT1Request product);
	
	ProductT1Response getProduct(long id);
	
	List<ProductT1Response> getProducts(ProductCategory category);
	
	ProductT1Response updateProduct(long id, UpdateProductRequest updRequest);
	
	void deleteProduct(long id);
}
