package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Integer>{

}
