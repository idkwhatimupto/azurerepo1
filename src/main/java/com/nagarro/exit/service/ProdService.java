package com.nagarro.exit.service;

import java.util.List;

import com.nagarro.exit.model.Product;

public interface ProdService {
	Product addProduct(Product product);

	List<Product> SearchProducts(String name, String brand, Long code);

	Product getProductById(Long id);

	void updateProductStats(Long productId);

}
