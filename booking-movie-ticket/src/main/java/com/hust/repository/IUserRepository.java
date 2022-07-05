package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	User findByUsername(String username);

	User findByEmail(String email);
}
