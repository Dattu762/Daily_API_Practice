package com.DailyPract.DTO;

import java.math.BigInteger;

import com.DailyPract.Enum.ProductCategory;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductT1Response {
	
	private Long id;
	private String name;
	private String sku;
	private String description;
	private BigInteger price;
	private Integer quantity;
	private ProductCategory category;
}
