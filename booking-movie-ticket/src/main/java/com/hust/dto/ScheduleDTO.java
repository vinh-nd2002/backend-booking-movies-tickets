package com.hust.dto;

import java.util.Date;

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
	}
}
