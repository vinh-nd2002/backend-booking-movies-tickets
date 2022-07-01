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
public class MovieDTO extends RepresentationModel<MovieDTO> {
	private int movieId;

	private String movieName;

	private String movieDescription;

	private String movieTrailer;

	private Date movieRelease;

	private short movieLength;

	private short movieEvaluate;

	private boolean movieStatus;

	private int moviePrice;

	private List<ScheduleMovieDTO> scheduleMovies;

	@Data
	@NoArgsConstructor
	static class ScheduleMovieDTO {
		private int scheduleMovieId;

		private Date scheduleDate;

		private ScheduleDTO schedule;

		@Data
		@NoArgsConstructor
		static class ScheduleDTO {
			private int scheduleId;

			private Date scheduleStart;
		}
	}

	@JsonUnwrapped
	private ImageDTO moviePoster;

	@Data
	@NoArgsConstructor
	static class ImageDTO {
		private int imgId;

		@JsonProperty("moviePoster")
		private String imgUrl;
	}

}
