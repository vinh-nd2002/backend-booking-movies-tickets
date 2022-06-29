package com.hust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.hust.dto.BookingSeatSocketDTO;

import com.hust.form.create.BookingTicketForm;
import com.hust.services.IBookingService;

@Controller
public class BookingController {

	private List<BookingSeatSocketDTO> listSeatSocketDTOs;

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	public BookingController(List<BookingSeatSocketDTO> seatSocketDTOs) {
		this.listSeatSocketDTOs = seatSocketDTOs;
	}

	// Đặt vé
	@MessageMapping("/booking-seats")
	public void bookingTickets(@Payload BookingTicketForm bookingTicketForm) {
		bookingService.bookingTickets(bookingTicketForm);
		BookingSeatSocketDTO temp = listSeatSocketDTOs.stream()
				.filter(item -> bookingTicketForm.getUsername().equals(item.getUsername())).findAny().orElse(null);
		listSeatSocketDTOs.remove(temp);
		simpMessagingTemplate.convertAndSend("/schedule-movie/" + bookingTicketForm.getScheduleMovieId(),
				listSeatSocketDTOs);
	}

	// Đặt vé thành công
	@MessageMapping("/booking-success")
	public void bookingSuccess(@Payload int scheduleMovieId) {
		simpMessagingTemplate.convertAndSend("/schedule-movie/" + scheduleMovieId + "/success", "success");
	}

	// Load danh sách ghế ngồi đang được chọn
	@MessageMapping("/load-booking-seats")
	public void loadBookingSeats(@Payload BookingSeatSocketDTO seatFromClient) {
		if (listSeatSocketDTOs.isEmpty()) {
			listSeatSocketDTOs.add(seatFromClient);
			simpMessagingTemplate.convertAndSend("/schedule-movie/" + seatFromClient.getScheduleMovieId(),
					listSeatSocketDTOs);
		}

		BookingSeatSocketDTO temp = listSeatSocketDTOs.stream()
				.filter(item -> seatFromClient.getUsername().equals(item.getUsername())).findAny().orElse(null);
		listSeatSocketDTOs.remove(temp);
		if (seatFromClient.getSeats().isEmpty() == false) {
			listSeatSocketDTOs.add(seatFromClient);
		}
		simpMessagingTemplate.convertAndSend("/schedule-movie/" + seatFromClient.getScheduleMovieId(),
				listSeatSocketDTOs);
	}

	// Load danh sách ghế ngồi đang được chọn (Sau khi vào sẽ được load ngay)
	@MessageMapping("/first-load-seats")
	public void firstLoadSeats(@Payload int id) {
		simpMessagingTemplate.convertAndSend("/schedule-movie/" + id, listSeatSocketDTOs);
	}

	// Load lại danh sách ghế ngồi khi có ai đó thoát ra
	@MessageMapping("/reset-seats")
	public void resetSeats(@Payload BookingSeatSocketDTO seatFromClient) {

		BookingSeatSocketDTO temp = listSeatSocketDTOs.stream()
				.filter(item -> seatFromClient.getUsername().equals(item.getUsername())).findAny().orElse(null);

		listSeatSocketDTOs.remove(temp);
		simpMessagingTemplate.convertAndSend("/schedule-movie/" + seatFromClient.getScheduleMovieId(),
				listSeatSocketDTOs);
	}

}
