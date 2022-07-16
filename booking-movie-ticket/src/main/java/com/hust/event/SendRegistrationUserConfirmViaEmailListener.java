package com.hust.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hust.services.IEmailService;

@Component
public class SendRegistrationUserConfirmViaEmailListener
		// Lắng nghe và xử lý sự kiện: Send email to confirm user
		implements ApplicationListener<OnSendRegistrationUserConfirmViaEmailEvent> {

	@Autowired
	private IEmailService emailService;

	@Override
	public void onApplicationEvent(OnSendRegistrationUserConfirmViaEmailEvent event) {
		sendConfirmViaEmail(event.getEmail());
	}

	private void sendConfirmViaEmail(String email) {

		// send email
		emailService.sendRegistrationUserConfirm(email);
	}

}