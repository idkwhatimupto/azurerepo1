package com.nagarro.exit.service;

import com.nagarro.exit.model.User;

public interface UserService {
	User saveUser(User user);

	User authenticateUser(String email, String password);

	User authenticateAdmin(String email, String password);

	User getUserById(Long id);

}
