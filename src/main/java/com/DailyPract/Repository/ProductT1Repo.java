package com.DailyPract.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DailyPract.DTO.ProductT1Response;
import com.DailyPract.Entity.ProductT1;
import com.DailyPract.Enum.ProductCategory;

@Repository
public interface ProductT1Repo extends JpaRepository<ProductT1, Long> {
	
	boolean existsBySku(String sku);
	List<ProductT1> findByCategory(ProductCategory category);
	
	@Query("Select p From ProductT1 p where p.isDeleted = false")
	List<ProductT1> getAllActiveProducts();
	
	@Query("Select p from ProductT1 p where (:category IS NULL OR p.category=:category) AND p.isDeleted=false")
	List<ProductT1> getAllActiveProductsByCategory(@Param("category") ProductCategory category);
}
