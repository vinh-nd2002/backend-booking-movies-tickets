package com.hust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.entity.User;
import com.hust.repository.IUserRepository;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private IUserRepository repository;

	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public User getUserById(int id) {
		return repository.getById(id);
	}

	@Override
	public User findAccountByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRole().toString()));
	}

}
