package com.DailyPract.DTO;

import java.math.BigInteger;

import org.springframework.validation.annotation.Validated;

import com.DailyPract.Enum.ProductCategory;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Getter
@Setter
public class ProductT1Request {
	
	@NotBlank(message="The name cannot be empty")
    @Size(max = 150, message = "Name must not exceed 150 characters")
	private String name;
	
	@NotBlank(message="The SKU cannot be empty")
	@Size(max = 50, message = "SKU must not exceed 50 characters")
	private String sku;
	
	@Size(min = 10, max=500, message = "Description cannot exceed 500 characters")
	private String description;
	
	@NotNull(message = "Price is required")
	private BigInteger price;
	
	@NotNull(message = "quantity is required")
	@Min(value = 0, message = "Quantity cannot be negative")
	private Integer quantity;
	
	@NotNull(message = "Category is required")
	private ProductCategory category;
}
