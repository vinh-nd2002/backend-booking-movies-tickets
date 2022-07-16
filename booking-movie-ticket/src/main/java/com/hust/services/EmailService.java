package com.hust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hust.entity.User;
import com.hust.repository.IRegistrationUserTokenRepository;
import com.hust.repository.IResetPasswordTokenRepository;

@Service
public class EmailService implements IEmailService {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRegistrationUserTokenRepository registrationUserTokenRepository;

	@Autowired
	private IResetPasswordTokenRepository resetPasswordTokenRepository;

	// Đối tượng sẽ gửi mail
	@Autowired
	private JavaMailSender mailSender;

	// Xử lý sự kiện send email to confirm user
	@Override
	public void sendRegistrationUserConfirm(String email) {
		User user = userService.findUserByEmail(email);

		String tokenConfirm = registrationUserTokenRepository.findTokenByUserId(user.getUserId());

		String confirmationUrl = "http://localhost:8080/api/v1/users/active-user?token=" + tokenConfirm;

		String subject = "Xác Nhận Đăng Ký Thành Viên Mới";
		String content = "Bạn đã đăng ký thành công. Click vào link dưới đây để kích hoạt tài khoản \n"
				+ confirmationUrl;

		// send mail to user confirm
		sendEmail(email, subject, content);
	}

	// Xử lý sự kiện send email to reset password
	@Override
	public void sendResetPassword(String email) {

		// find user
		User user = userService.findUserByEmail(email);

		String tokenResetPassword = resetPasswordTokenRepository.findByUserId(user.getUserId());

		String resetPasswordUrl = "http://localhost:3000/auth/new-password/" + tokenResetPassword;

		String subject = "Xác Nhận Thiết Lập Lại Mật Khẩu Mới";
		String content = "Xin chào " + user.getLastName()
				+ ", chúng tôi đã nhận được yêu cầu cấp lại mật khẩu mới của bạn.\n"
				+ "Click vào link dưới đây để thiết lập lại mật khẩu .\n" + resetPasswordUrl;

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
