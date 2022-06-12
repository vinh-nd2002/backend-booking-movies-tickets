package com.hust.dto;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CineplexDTO extends RepresentationModel<CineplexDTO> {
	private short cineplexId;

	private String cineplexCode;

	private String cineplexName;

	@JsonUnwrapped
	private ImageDTO cineplexLogo;

	@Data
	@NoArgsConstructor
	public static class ImageDTO {
//		private int imgId;

		@JsonProperty("cineplexLogo")
		private String imgUrl;
	}

	private List<CinemaDTO> cinemas;

	@Data
	@NoArgsConstructor
	public static class CinemaDTO extends RepresentationModel<CinemaDTO> {
		private int cinemaId;

		private String cinemaName;

		private String cinemaAddress;

		private List<RoomDTO> rooms;

		@Data
		@NoArgsConstructor
		static class RoomDTO {
			private int roomId;

			private String roomName;

			private List<ScheduleMovieDTO> scheduleMovies;

			@Data
			@NoArgsConstructor
			static class ScheduleMovieDTO {
				private int scheduleMovieId;

				@JsonUnwrapped
				private ScheduleDTO schedule;

				@Data
				@NoArgsConstructor
				static class ScheduleDTO {
					private int scheduleId;

					private Date scheduleStart;

				}

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
//						private int imgId;

						@JsonProperty("moviePoster")
						private String imgUrl;
					}
				}
			}
		}
	}

}
