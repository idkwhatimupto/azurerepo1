package com.nagarro.exit.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nagarro.exit.model.Product;
import com.nagarro.exit.repository.ProdRepository;
import com.nagarro.exit.repository.ReviewRepository;
import com.nagarro.exit.service.ProdService;

@Service
public class ProdServiceImpl implements ProdService {
	ProdRepository prodRepo;
	ReviewRepository reviewRepo;

	ProdServiceImpl(ProdRepository prodRepo, ReviewRepository reviewRepo) {
		this.prodRepo = prodRepo;
		this.reviewRepo = reviewRepo;
	}

	@Override
	public Product addProduct(Product product) {
		if (prodRepo.existsById(product.getCode())) {
			throw new DataIntegrityViolationException("product already exists");
		}
		return prodRepo.save(product);
	}

	@Override
	public List<Product> SearchProducts(String name, String brand, Long code) {
		List<Product> products = prodRepo.findByParams(name, brand, code);
		return products;
	}

	@Override
	public Product getProductById(Long id) {
		return prodRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
	}

	@Override
	public void updateProductStats(Long productId) {
		float avgRating = reviewRepo.calculateAverageRating(productId);
		int reviewCount = reviewRepo.countByProductCodeAndApprovedIsTrue(productId);

		prodRepo.updateProductStats(productId, avgRating, reviewCount);

	}

}
