package com.nagarro.exit.service;

import java.util.List;

import com.nagarro.exit.model.Review;

public interface ReviewService {
	Review addReview(Review review);

	Review approve(long id);

	List<Review> getProductReviews(Long productId);

	List<Review> getPendingReviews();

	Review deleteReview(Long id);
}
