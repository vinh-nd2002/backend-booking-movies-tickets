package com.hust.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hust.services.IEmailService;

@Component
public class SendResetPasswordViaEmailListener
		// Lắng nghe và xử lý sự kiện: Send email to reset password
		implements ApplicationListener<OnResetPasswordViaEmailEvent> {

	@Autowired
	private IEmailService emailService;

	@Override
	public void onApplicationEvent(OnResetPasswordViaEmailEvent event) {
		sendResetPasswordViaEmail(event.getEmail());
	}

	private void sendResetPasswordViaEmail(String email) {
		// send email
		emailService.sendResetPassword(email);

	}

}