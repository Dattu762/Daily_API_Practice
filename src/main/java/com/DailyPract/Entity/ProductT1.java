package com.DailyPract.Entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.DailyPract.Enum.ProductCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Product")
public class ProductT1 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 150)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String sku;
	
	@Column(length = 500)
	private String description;
	
	@Column(nullable = false)
	private BigInteger price;
	
	@Column(nullable=false)
	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ProductCategory category;
	
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@Column(nullable = false)
	private Boolean isDeleted=false;
	
	@PrePersist
	private void OnCreate() {
		this.createdAt=LocalDateTime.now();
		this.updatedAt=LocalDateTime.now();
		if (this.isDeleted == null) {
            this.isDeleted = false;
        }
	}
	
	@PreUpdate
	private void OnUpdate() {
		this.updatedAt=LocalDateTime.now();
	}
}
