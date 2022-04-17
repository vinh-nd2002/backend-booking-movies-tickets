package com.hust.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CineplexDTO {
	private short cineplexId;

	private String cineplexCode;

	private String cineplexName;

	private String cineplexLogo;

	private List<CinemaDTO> cinemas;

	@Data
	@NoArgsConstructor
	static class CinemaDTO {
		private int cinemaId;
		private String cinemaCode;
		private String cinemaName;
		private String cinemaAddress;
	}

}
