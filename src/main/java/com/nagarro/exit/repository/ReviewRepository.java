package com.nagarro.exit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.exit.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Long countByApprovedTrue();

	@Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId AND r.approved = 1")
	Float calculateAverageRating(long productId);

	int countByProductCodeAndApprovedIsTrue(long productId);

	List<Review> findAllByProductCodeAndApprovedIsTrue(Long productId);

	List<Review> findAllByApprovedFalse();

	Optional<Review> findById(Long id);

}
