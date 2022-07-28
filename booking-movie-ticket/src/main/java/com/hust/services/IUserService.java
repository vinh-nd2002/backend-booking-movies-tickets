package com.hust.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hust.entity.User;
import com.hust.form.create.UserForm;

public interface IUserService extends UserDetailsService {

	User findUserByUsername(String username);

	User findUserByEmail(String email);

	List<User> getAllUsers();

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
