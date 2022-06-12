package com.hust.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hust.entity.Seat.SeatType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDTO {
	private int ticketCode;

	@JsonProperty("scheduleMovie")
	private ScheduleMovieDTO ticketsOfScheduleMovie;

	@Data
	@NoArgsConstructor
	static class ScheduleMovieDTO extends RepresentationModel<ScheduleMovieDTO> {
		private int scheduleMovieId;

		private MovieDTO movie;

		@Data
		@NoArgsConstructor
		static class MovieDTO extends RepresentationModel<MovieDTO> {
			private int movieId;

			private String movieName;

			@JsonUnwrapped
			private ImageDTO moviePoster;

			@Data
			@NoArgsConstructor
			static class ImageDTO {
				@JsonProperty("moviePoster")
				private String imgUrl;
			}

		}
	}

	@JsonUnwrapped
	@JsonProperty("seat")
	private SeatDTO ticketsOfSeat;

	@Data
	@NoArgsConstructor
	static class SeatDTO {
		private SeatType seatType;

		private short seatNumber;
	}

	@JsonProperty("user")
	private UserDTO ticketsOfAccount;

	@Data
	@NoArgsConstructor
	static class UserDTO extends RepresentationModel<UserDTO> {

		private String username;

		private String numberPhone;
	}

	private int ticketPrice;

	private Date createdDate;
}
