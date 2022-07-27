package com.hust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.entity.User;
import com.hust.event.OnSendSuccessfulTicketBooking;
import com.hust.entity.ScheduleMovie;
import com.hust.entity.Seat;
import com.hust.entity.Ticket;
import com.hust.form.create.BookingTicketForm;
import com.hust.repository.IUserRepository;
import com.hust.utils.RandomString;
import com.hust.repository.IScheduleMovieRepository;
import com.hust.repository.ISeatRepository;
import com.hust.repository.ITicketRepository;

@Service
@Transactional
public class BookingService implements IBookingService {

	@Autowired
	private ITicketRepository ticketRepository;

	@Autowired
	private IScheduleMovieRepository scheduleMovieRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ISeatRepository seatRepository;

	@Autowired
	private RandomString randomString;

	// Thông báo event
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Override
	public void bookingTickets(BookingTicketForm bookingTicketForm) {
		ScheduleMovie scheduleMovie = scheduleMovieRepository.getById(bookingTicketForm.getScheduleMovieId());
		User user = userRepository.findByUsername(bookingTicketForm.getUsername());

		// save tickets
		bookingTicketForm.getSeats().stream().forEach((item) -> {
			Ticket ticket = new Ticket();
			Seat seat = seatRepository.getById(item.getSeatId());
			ticket.setTicketCode(randomString.randomString());
			ticket.setTicketPrice(item.getTicketPrice());
			ticket.setTicketsOfUser(user);
			ticket.setTicketsOfSeat(seat);
			ticket.setTicketsOfScheduleMovie(scheduleMovie);
			ticketRepository.save(ticket);
		});
	}

	@Override
	public void sendSuccessfulTicketBookingViaEmail(String email, int scheduleMovieId) {
		eventPublisher.publishEvent(new OnSendSuccessfulTicketBooking(email, scheduleMovieId));

	}

}
