package com.hust.dto;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

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

	private short movieLenght;

	private short movieEvaluate;

	private boolean movieMtatus;

	private int moviePrice;

	private List<ScheduleDTO> schedules;

	@Data
	@NoArgsConstructor
	public static class ScheduleDTO {
		private int scheduleId;

		private Date scheduleDate;

		private Date scheduleStart;

	}

	private ImageDTO moviePoster;

	@Data
	@NoArgsConstructor
	public static class ImageDTO {
		private int imgId;

		private String imgUrl;

	}

}
