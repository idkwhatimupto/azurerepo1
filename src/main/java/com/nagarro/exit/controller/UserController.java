package com.nagarro.exit.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exit.model.User;
import com.nagarro.exit.service.UserService;

@CrossOrigin("*")
@RestController
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/api/addUser")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("email already exists");
		}

	}

	@PostMapping("/api/authenticateUser")
	public ResponseEntity<User> authenticateUser(@RequestBody User user) {
		User authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());
		if (authenticatedUser != null) {
			return ResponseEntity.ok(authenticatedUser);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@PostMapping("/api/authenticateAdmin")
	public ResponseEntity<User> authenticateAdmin(@RequestBody User user) {
		User authenticatedAdmin = userService.authenticateAdmin(user.getEmail(), user.getPassword());
		if (authenticatedAdmin != null) {
			return ResponseEntity.ok(authenticatedAdmin);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
