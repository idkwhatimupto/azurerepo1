package com.nagarro.exit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.exit.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	Long countByAdminFalse();

}
