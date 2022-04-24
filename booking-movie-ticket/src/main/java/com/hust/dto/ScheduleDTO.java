package com.hust.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.hust.entity.Seat.SeatType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleDTO {
	private int scheduleId;

	private Date scheduleDate;

	private Date scheduleStart;

	private RoomDTO scheduleOfRoom;

	@Data
	@NoArgsConstructor
	static class RoomDTO {
		private int roomId;

		@Data
		@NoArgsConstructor
		static class SeatDTO {
			private int seatId;

			private SeatType seatType;

			private short seatNumber;

			private boolean seatStatus;

		}
	}

	@Data
	@NoArgsConstructor
	static class MovieDTO extends RepresentationModel<MovieDTO> {
		private int movieId;

		private String movieName;

		private short movieLenght;

		private String moviePoster;

		private boolean movieMtatus;

		private int moviePrice;
	}
}
