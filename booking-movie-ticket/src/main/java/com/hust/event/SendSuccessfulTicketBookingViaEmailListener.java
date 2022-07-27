package com.hust.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hust.services.IEmailService;

@Component
public class SendSuccessfulTicketBookingViaEmailListener implements ApplicationListener<OnSendSuccessfulTicketBooking> {

	@Autowired
	private IEmailService emailService;

	@Override
	public void onApplicationEvent(OnSendSuccessfulTicketBooking event) {
		sendSuccessTicketBookingViaEmail(event.getEmail(), event.getScheduleMovieId());

	}

	private void sendSuccessTicketBookingViaEmail(String email, int scheduleMovieId) {

		// send email
		emailService.sendSuccessTicketBooking(email, scheduleMovieId);
	}

}
