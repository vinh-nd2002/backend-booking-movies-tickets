package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.entity.ResetPasswordToken;

public interface IResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Integer>{

}
