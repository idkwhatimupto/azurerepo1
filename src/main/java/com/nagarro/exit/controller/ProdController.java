package com.nagarro.exit.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exit.model.Product;
import com.nagarro.exit.service.ProdService;

@CrossOrigin("*")
@RestController
public class ProdController {

	ProdService prodService;

	ProdController(ProdService prodService) {
		super();
		this.prodService = prodService;
	}

	@PostMapping("/api/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {

		try {
			return new ResponseEntity<Product>(prodService.addProduct(product), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

	@GetMapping("/api/getProductByCode/{id}")
	public ResponseEntity<Product> getProductByCode(@PathVariable("id") long id) {
		return new ResponseEntity<Product>(prodService.getProductById(id), HttpStatus.OK);

	}

	@GetMapping("/api/searchProduct")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam(required = false) String name,
			@RequestParam(required = false) String brand, @RequestParam(required = false) Long code) {
		if (name == null || name.isEmpty()) {
			name = null;
		}
		if (code != null && code == 0L) {
			code = null;
		}
		if (brand == null || brand.isEmpty()) {
			brand = null;
		}

		return new ResponseEntity<List<Product>>(prodService.SearchProducts(name, brand, code), HttpStatus.OK);

	}
}
