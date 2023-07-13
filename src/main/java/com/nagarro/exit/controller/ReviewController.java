package com.nagarro.exit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exit.model.Product;
import com.nagarro.exit.model.Review;
import com.nagarro.exit.model.ReviewDTO;
import com.nagarro.exit.model.User;
import com.nagarro.exit.service.ProdService;
import com.nagarro.exit.service.ReviewService;
import com.nagarro.exit.service.UserService;

@CrossOrigin("*")
@RestController
public class ReviewController {

	ReviewService revService;
	ProdService prodService;
	UserService userService;

	public ReviewController(ReviewService revService, ProdService prodService, UserService userService) {
		super();
		this.revService = revService;
		this.prodService = prodService;
		this.userService = userService;
	}

	@GetMapping("/api/reviews/getProductReviews/{id}")
	public ResponseEntity<List<Review>> getProductReviews(@PathVariable("id") long id) {
		return new ResponseEntity<List<Review>>(revService.getProductReviews(id), HttpStatus.OK);

	}

	@GetMapping("/api/reviews/getPendingReviews")
	public ResponseEntity<List<Review>> getPendingReviews() {
		return new ResponseEntity<List<Review>>(revService.getPendingReviews(), HttpStatus.OK);

	}

	@PutMapping("/api/reviews/approve/{id}")
	public ResponseEntity<Review> approveReview(@PathVariable("id") long id) {
		return new ResponseEntity<Review>(revService.approve(id), HttpStatus.OK);

	}

	@DeleteMapping("api/reviews/deleteReview/{id}")
	public ResponseEntity<Review> deleteReview(@PathVariable("id") Long id) {
		return new ResponseEntity<Review>(revService.deleteReview(id), HttpStatus.OK);
	}

	@PostMapping("/api/addReview")
	public ResponseEntity<Review> addReview(@RequestBody ReviewDTO reviewDTO) {
		// Retrieve the associated product based on the product ID
		Product product = prodService.getProductById(reviewDTO.getProductCode());
		User user = userService.getUserById(reviewDTO.getUserId());

		// Create a new Review object
		Review review = new Review();
		review.setRating(reviewDTO.getRating());
		review.setContent(reviewDTO.getContent());
		review.setHeading(reviewDTO.getHeading());
		review.setProduct(product);
		review.setUser(user);

		return new ResponseEntity<Review>(revService.addReview(review), HttpStatus.CREATED);
	}

}
