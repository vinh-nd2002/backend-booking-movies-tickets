package com.hust.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hust.entity.Account;

public interface IAccountService {

	Page<Account> getAllAccounts(Pageable pageable);

	Account getAccountById(int id);

}
