package com.hust.services;

public interface IEmailService {

	void sendRegistrationUserConfirm(String email);

	void sendResetPassword(String email);

}
