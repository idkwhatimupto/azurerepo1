package com.nagarro.exit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.exit.model.Product;

public interface ProdRepository extends JpaRepository<Product, Long> {
	Product findByCode(Long code);

	@Query("SELECT p FROM Product p WHERE " + "(:name IS NULL OR p.name = :name) AND "
			+ "(:brand IS NULL OR p.brand = :brand) AND " + "(:code IS NULL OR p.code= :code)")
	List<Product> findByParams(@Param("name") String name, @Param("brand") String brand, @Param("code") Long code);

	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.avgRating = :avgRating, p.reviewCount = :reviewCount WHERE p.code = :productId")
	void updateProductStats(long productId, float avgRating, int reviewCount);

}