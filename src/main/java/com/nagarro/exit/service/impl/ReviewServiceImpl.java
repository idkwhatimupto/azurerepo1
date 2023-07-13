package com.nagarro.exit.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.nagarro.exit.model.Review;
import com.nagarro.exit.repository.ReviewRepository;
import com.nagarro.exit.service.ProdService;
import com.nagarro.exit.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	ReviewRepository reviewRepo;
	ProdService prodService;

	ReviewServiceImpl(ReviewRepository reviewRepo, ProdService prodService) {
		super();
		this.reviewRepo = reviewRepo;
		this.prodService = prodService;
	}

	@Override
	public Review addReview(Review review) {
		return reviewRepo.save(review);

	}

	@Override
	public Review approve(long id) {
		Review review = reviewRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Review not found with ID: " + id));

		review.setApproved(true);
		Review tempReview = reviewRepo.save(review);
		prodService.updateProductStats(tempReview.getProduct().getCode());
		return tempReview;

	}

	@Override
	public List<Review> getProductReviews(Long productId) {
		return reviewRepo.findAllByProductCodeAndApprovedIsTrue(productId);
	}

	@Override
	public List<Review> getPendingReviews() {

		return reviewRepo.findAllByApprovedFalse();
	}

	@Override
	public Review deleteReview(Long id) {
		Review existingReview = reviewRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));

		reviewRepo.delete(existingReview);
		return existingReview;
	}

}
