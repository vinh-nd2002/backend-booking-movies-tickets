package com.hust.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hust.entity.Ticket.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDTO {
	private int ticketId;

	private String ticketCode;

	@JsonProperty("movie")
	@JsonUnwrapped
	private ScheduleMovieDTO ticketsOfScheduleMovie;

	@Data
	@NoArgsConstructor
	static class ScheduleMovieDTO {
		@JsonUnwrapped
		private MovieDTO movie;

		@Data
		@NoArgsConstructor
		static class MovieDTO extends RepresentationModel<MovieDTO> {

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

	@JsonProperty("user")
	private UserDTO ticketsOfUser;

	@Data
	@NoArgsConstructor
	static class UserDTO extends RepresentationModel<UserDTO> {

		private String username;

		private String numberPhone;
	}

	private int ticketPrice;

	private Status ticketStatus;

	private Date createdDate;
}
