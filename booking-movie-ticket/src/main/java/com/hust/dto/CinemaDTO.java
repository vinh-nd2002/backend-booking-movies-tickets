package com.hust.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CinemaDTO {
	private int cinemaId;
	
	private String cinemaCode;

	private String cinemaName;

	private String cinemaAddress;

	private List<RoomDTO> rooms;

	@Data
	@NoArgsConstructor
	static class RoomDTO {
		private int roomId;
		private String roomName;
	}
}
