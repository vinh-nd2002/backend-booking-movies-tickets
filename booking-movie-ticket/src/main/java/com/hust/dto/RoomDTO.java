package com.hust.dto;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomDTO {
	private int roomId;

	private String roomName;

	private List<ScheduleMovieDTO> scheduleMovies;

	@Data
	@NoArgsConstructor
	static class ScheduleMovieDTO {
		private int scheduleMovieId;

		@JsonFormat(pattern = "dd/MM/YYYY")
		private Date scheduleDate;

		private MovieDTO movie;

		@Data
		@NoArgsConstructor
		static class MovieDTO extends RepresentationModel<MovieDTO> {
			private int movieId;

			private String movieName;

			private boolean movieStatus;

			@JsonUnwrapped
			private ImageDTO moviePoster;

			@Data
			@NoArgsConstructor
			static class ImageDTO {

				private String imgUrl;
			}
		}

		private ScheduleDTO schedule;

		@Data
		@NoArgsConstructor
		static class ScheduleDTO {
			private int scheduleId;

			private Date scheduleStart;

		}
	}
}
