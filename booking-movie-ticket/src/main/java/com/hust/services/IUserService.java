package com.hust.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hust.entity.User;
import com.hust.form.create.UserForm;

public interface IUserService extends UserDetailsService {

	User findUserByUsername(String username);

	User findUserByEmail(String email);

	Page<User> getAllUsers(Pageable pageable);

	boolean isUserExistsByEmail(String email);

	boolean isUserExistsByUsername(String username);

	User getUserById(int id);

	void createUser(UserForm form);

	void activeUser(String token);

	void sendConfirmUserRegistrationViaEmail(String email);

	void resetPasswordViaEmail(String email);

	void sendResetPasswordViaEmail(String email);

	void resetPassword(String token, String newPassword);

}
