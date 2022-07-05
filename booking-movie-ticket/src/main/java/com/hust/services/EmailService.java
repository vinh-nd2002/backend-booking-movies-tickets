package com.hust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hust.entity.RegistrationUserToken;
import com.hust.entity.User;
import com.hust.repository.IRegistrationUserTokenRepository;

@Service
public class EmailService implements IEmailService {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRegistrationUserTokenRepository registrationUserTokenRepository;

	@Autowired
	private IRegistrationUserTokenRepository resetPasswordTokenRepository;

	// Đối tượng sẽ gửi mail
	@Autowired
	private JavaMailSender mailSender;

	// Xử lý sự kiện send email to confirm user
	@Override
	public void sendRegistrationUserConfirm(String email) {
		User user = userService.findAccountByEmail(email);

		String tokenConfirm = registrationUserTokenRepository.findTokenByUserId(user.getUserId());

		String confirmationUrl = "http://localhost:8080/api/v1/users/active-user?token=" + tokenConfirm;

		String subject = "Xác Nhận Đăng Ký Account";
		String content = "Bạn đã đăng ký thành công. Click vào link dưới đây để kích hoạt tài khoản \n"
				+ confirmationUrl;

		// send mail to user confirm
		sendEmail(email, subject, content);
	}

	// config send mail
	private void sendEmail(final String recipientEmail, final String subject, final String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipientEmail);
		message.setSubject(subject);
		message.setText(content);

		mailSender.send(message);
	}

}
