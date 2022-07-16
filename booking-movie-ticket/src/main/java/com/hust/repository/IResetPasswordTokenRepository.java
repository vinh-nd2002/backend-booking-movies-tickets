package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hust.entity.ResetPasswordToken;

public interface IResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Integer> {

	@Transactional
	@Modifying
	@Query("	DELETE 						"
			+ "	FROM 	ResetPasswordToken 	" 
			+ " 	WHERE 	user.id = :userId")
	void deleteByUserId(@Param("userId") int userId);

	@Query("	SELECT 	token	"
			+ "	FROM 	ResetPasswordToken "
			+ " WHERE 	user.id = :userId")
	String findByUserId(@Param("userId") int userId);

	ResetPasswordToken findByToken(String token);

}
