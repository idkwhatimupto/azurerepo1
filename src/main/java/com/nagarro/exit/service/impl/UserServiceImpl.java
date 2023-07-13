package com.nagarro.exit.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.nagarro.exit.model.User;
import com.nagarro.exit.repository.UserRepository;
import com.nagarro.exit.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {

		return userRepository.save(user);

	}

	@Override
	public User authenticateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null && !user.isAdmin() && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public User authenticateAdmin(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null && user.isAdmin() && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
	}

}
