package com.nagarro.exit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exit.repository.ProdRepository;
import com.nagarro.exit.repository.ReviewRepository;
import com.nagarro.exit.repository.UserRepository;
import com.nagarro.exit.service.AllStatsService;

@Service
public class AllStatsServiceImpl implements AllStatsService {

	private final UserRepository userRepository;
	private final ProdRepository productRepository;
	private final ReviewRepository reviewRepository;

	@Autowired
	public AllStatsServiceImpl(UserRepository userRepository, ProdRepository productRepository,
			ReviewRepository reviewRepository) {
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.reviewRepository = reviewRepository;
	}

	@Override
	public Long getReviewCount() {

		return reviewRepository.countByApprovedTrue();
	}

	@Override
	public Long getProdCount() {
		return productRepository.count();
	}

	@Override
	public Long getUserCount() {
		return userRepository.countByAdminFalse();
	}

}
