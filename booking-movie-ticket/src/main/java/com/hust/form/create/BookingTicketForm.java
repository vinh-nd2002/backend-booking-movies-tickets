package com.hust.form.create;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingTicketForm {

	private int scheduleMovieId;

	private List<SeatForm> seats;

	@Data
	@NoArgsConstructor
	public static class SeatForm {
		private int seatId;

		private int ticketPrice;
	}

	private String username;

}
