package com.DailyPract.DTO;

import java.math.BigInteger;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import com.DailyPract.Enum.ProductCategory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Setter
@Getter
@Validated
public class UpdateProductRequest {
	
	@NotBlank(message = "Product name is required")
	private String name;
	
	@Size(min = 10, max=500, message = "Description cannot exceed 500 characters")
	private String description;
	
	@NotNull(message = "price is required")
	@Min(value=1, message = "Price must be greater than zero.")
	private BigInteger price;
	
	@NotNull(message = "quantity is required")
	@Min(value = 0, message = "Quantity cannot be negative")
	private Integer quantity;
	
	@NotNull(message = "Category is required")
	private ProductCategory category;
	
}
