package com.hust.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hust.entity.Seat;
import com.hust.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleMovieDTO extends RepresentationModel<ScheduleMovieDTO> {

	private int scheduleMovieId;

	@JsonUnwrapped
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

		private int moviePrice;
	}

	@JsonUnwrapped
	private ScheduleDTO schedule;

	@Data
	@NoArgsConstructor
	static class ScheduleDTO {
		private Date scheduleStart;

	}

	@JsonUnwrapped
	private RoomDTO room;

	@Data
	@NoArgsConstructor
	static class RoomDTO {
		private String roomName;

		@JsonUnwrapped
		private CinemaDTO roomsOfCinema;

		@Data
		@NoArgsConstructor
		static class CinemaDTO {
			private String cinemaName;

			@JsonUnwrapped
			private CineplexDTO cinemasOfCineplex;

			@Data
			@NoArgsConstructor
			static class CineplexDTO {
				@JsonUnwrapped
				private ImageDTO cineplexLogo;

				@Data
				@NoArgsConstructor
				static class ImageDTO {
					@JsonProperty("logo")
					private String imgUrl;
				}

			}
		}
	}

	private List<TicketDTO> tickets;

	@Data
	@NoArgsConstructor
	static class TicketDTO {
		
		@JsonUnwrapped
		private SeatDTO ticketsOfSeat;

		@Data
		@NoArgsConstructor
		static class SeatDTO {
			private short seatNumber;
		}

		@JsonUnwrapped
		private UserDTO ticketsOfUser;

		@Data
		@NoArgsConstructor
		static class UserDTO {
			private String username;
		}
	}

	private Date scheduleDate;
}
