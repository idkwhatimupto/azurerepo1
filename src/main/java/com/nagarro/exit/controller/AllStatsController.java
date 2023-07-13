package com.nagarro.exit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exit.service.AllStatsService;

@CrossOrigin("*")
@RestController
public class AllStatsController {

	AllStatsService allStatsService;

	@Autowired
	AllStatsController(AllStatsService allStatsService) {
		this.allStatsService = allStatsService;
	}

	@GetMapping("/api/stats/reviewCount")
	public ResponseEntity<Long> GetReviewCount() {
		return ResponseEntity.ok(allStatsService.getReviewCount());
	}

	@GetMapping("/api/stats/productCount")
	public ResponseEntity<Long> GetProductCount() {
		return ResponseEntity.ok(allStatsService.getProdCount());
	}

	@GetMapping("/api/stats/userCount")
	public ResponseEntity<Long> GetUserCount() {
		return ResponseEntity.ok(allStatsService.getUserCount());
	}

}
