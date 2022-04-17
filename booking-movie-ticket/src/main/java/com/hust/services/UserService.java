package com.hust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hust.entity.User;
import com.hust.repository.IUserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository repository;

	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
