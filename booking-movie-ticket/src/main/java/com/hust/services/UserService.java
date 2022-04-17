package com.hust.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.entity.User;
import com.hust.repository.IUserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository repository;
	
	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

}
