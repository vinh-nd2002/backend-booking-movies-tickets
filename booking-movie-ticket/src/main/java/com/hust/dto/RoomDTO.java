package com.hust.dto;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomDTO {
	private int roomId;

	private String roomName;

	private List<ScheduleDTO> schedules;

	@Data
	@NoArgsConstructor
	public static class ScheduleDTO {
		private int scheduleId;

		private Date scheduleDate;

		private Date scheduleStart;

		private MovieDTO scheduleOfMovie;

		@Data
		@NoArgsConstructor
		static class MovieDTO extends RepresentationModel<MovieDTO> {
			private int movieId;

			private Date movieRelease;

			private String movieName;

			private String moviePoster;

			private boolean status;
		}
	}
}
