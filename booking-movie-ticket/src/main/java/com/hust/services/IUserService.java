package com.hust.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hust.entity.User;

public interface IUserService extends UserDetailsService{

	User findAccountByUsername(String username);

	Page<User> getAllUsers(Pageable pageable);

	User getUserById(int id);

	
}
