package com.hust.services;

import com.hust.form.create.BookingTicketForm;

public interface IBookingService {

	void bookingTickets(BookingTicketForm bookingTicketForm);

	void sendSuccessfulTicketBookingViaEmail(String email, int scheduleMovieId);
}
