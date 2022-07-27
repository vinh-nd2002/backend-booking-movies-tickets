package com.hust.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hust.entity.Ticket;
import com.hust.entity.Ticket.Status;
import com.hust.entity.User;
import com.hust.repository.IRegistrationUserTokenRepository;
import com.hust.repository.IResetPasswordTokenRepository;
import com.hust.repository.ITicketRepository;

@Service
public class EmailService implements IEmailService {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRegistrationUserTokenRepository registrationUserTokenRepository;

	@Autowired
	private IResetPasswordTokenRepository resetPasswordTokenRepository;

	@Autowired
	private ITicketRepository ticketRepository;

	// Đối tượng sẽ gửi mail
	@Autowired
	private JavaMailSender mailSender;

	// config send mail
	private void sendEmail(final String recipientEmail, final String subject, final String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipientEmail);
		message.setSubject(subject);
		message.setText(content);

		mailSender.send(message);
	}

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

	// Xử lý sự kiện sau khi người dùng đặt vé thành công
	@Override
	public void sendSuccessTicketBooking(String email, int scheduleMovieId) {
		// find user
		User user = userService.findUserByEmail(email);

		List<Ticket> tickets = ticketRepository.findTicketsByUserId(user.getUserId());

		// filter user's ticket whose scheduleMovieId is scheduleMovieId and ticket
		// status is PENDING
		tickets = tickets.stream()
				.filter(item -> item.getTicketsOfScheduleMovie().getScheduleMovieId() == scheduleMovieId
						&& item.getTicketStatus().equals(Status.PENDING))
				.toList();

		// get ticket's code
		List<String> ticketCodes = new ArrayList<String>();
		tickets.stream().forEach(item -> ticketCodes.add(item.getTicketCode()));
		String subject = "Xác nhận đặt vé thành công";

		String content = "Xin chào " + user.getLastName()
				+ ", Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi. Dưới đây là các mã vé mà bạn đã đặt, hãy đưa nó đến rạp phim để lấy vé.\n"
				+ "Vui lòng không đưa mã vé cho bất kỳ ai để bảo đảm thông tin.\n" + "Danh sách mã của bạn.\n"
				+ ticketCodes.toString();

		sendEmail(email, subject, content);
	}

}
