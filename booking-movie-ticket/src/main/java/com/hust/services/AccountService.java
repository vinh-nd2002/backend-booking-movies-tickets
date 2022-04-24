package com.hust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hust.entity.Account;
import com.hust.repository.IAccountRepository;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository repository;

	@Override
	public Page<Account> getAllAccounts(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Account getAccountById(int id) {
		return repository.getById(id);
	}

}
