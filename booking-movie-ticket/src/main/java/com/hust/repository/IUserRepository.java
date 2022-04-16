package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.models.User;

public interface IUserRepository extends JpaRepository<User, Integer>{

}
