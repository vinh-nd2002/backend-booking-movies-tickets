package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hust.entity.RegistrationUserToken;

public interface IRegistrationUserTokenRepository extends JpaRepository<RegistrationUserToken, Integer> {

	@Query("	SELECT 	token	" + "	FROM 	RegistrationUserToken " + " WHERE 	user.userId = :userId")
	String findTokenByUserId(@Param("userId") int userId);

	RegistrationUserToken findByToken(String token);

}
