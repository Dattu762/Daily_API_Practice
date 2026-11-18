package com.DailyPract.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.DailyPract.DTO.ProductT1Request;
import com.DailyPract.DTO.ProductT1Response;
import com.DailyPract.DTO.UpdateProductRequest;
import com.DailyPract.Entity.ProductT1;
import com.DailyPract.Enum.ProductCategory;
import com.DailyPract.GlobalExceptionHandler.ProductAlreadyDeletedException;
import com.DailyPract.GlobalExceptionHandler.ProductAlreadyFoundException;
import com.DailyPract.GlobalExceptionHandler.ResourceNotFoundException;
import com.DailyPract.Repository.ProductT1Repo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductT1ServiceImpl implements ProductT1Service {
	

	private final ProductT1Repo repo;
	
	@Override
	public ProductT1Response createProduct(ProductT1Request request) {
		
		if(repo.existsBySku(request.getSku().trim())) {
			throw new ProductAlreadyFoundException("A product with this SKU already exists");
		}
		
		ProductT1 product=mapToProduct(request);
		
		ProductT1 savedProd= repo.save(product);
		
		return mapToProdResoponse(savedProd);
	}
	
	@Override
	public ProductT1Response getProduct(long id) {
		
		ProductT1 prod=repo.findById(id).filter(p->p.getIsDeleted()==false).orElseThrow(()->new ResourceNotFoundException("Product not found with id: " + id));
		
		ProductT1Response pres=mapToProdResoponse(prod);
		return pres;
	}
	
	@Override
	public List<ProductT1Response> getProducts(ProductCategory category) {
		// TODO Auto-generated method stub
		List<ProductT1> products=repo.getAllActiveProductsByCategory(category);
		
		//Do not include deleted products
		List<ProductT1Response> prods=products.stream().map(this::mapToProdResoponse).collect(Collectors.toList());
		
		return prods;
	}
	
	@Override
	@Transactional
	public ProductT1Response updateProduct(long id, UpdateProductRequest updRequest) {
		
		ProductT1 prod = repo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Product is already deleted with id: " + id));
		
		prod.setName(updRequest.getName());
		prod.setDescription(updRequest.getDescription());
		prod.setPrice(updRequest.getPrice());
		prod.setQuantity(updRequest.getQuantity());
		prod.setCategory(updRequest.getCategory());
		
		ProductT1 updatedProd=repo.save(prod);
		
		return mapToProdResoponse(updatedProd);
	}
	
	@Override
	@Transactional
	public void deleteProduct(long id) {
		
		ProductT1 product=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
		
		if(product.getIsDeleted()==true) throw new ProductAlreadyDeletedException("Product not found with id: " + id);
		
		product.setIsDeleted(true);
		
		repo.save(product);
		
	}
	
	
	
	
	private ProductT1Response mapToProdResoponse(ProductT1 product) {
		
		return ProductT1Response.builder()
		.id(product.getId())
		.name(product.getName())
		.sku(product.getSku())
		.description(product.getDescription())
		.category(product.getCategory())
		.quantity(product.getQuantity())
		.price(product.getPrice())
		.build();
	}
	
	private ProductT1 mapToProduct(ProductT1Request request) {
		return ProductT1.builder()
                .name(request.getName().trim())
                .sku(request.getSku().trim())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .build();
	}

	

	

	

}
