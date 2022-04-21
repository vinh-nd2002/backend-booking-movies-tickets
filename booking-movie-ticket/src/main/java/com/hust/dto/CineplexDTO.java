package com.hust.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CineplexDTO extends RepresentationModel<CineplexDTO> {
	private short cineplexId;

	private String cineplexCode;

	private String cineplexName;

	private String cineplexLogo;

	private List<CinemaDTO> cinemas;

	@Data
	@NoArgsConstructor
	public static class CinemaDTO extends RepresentationModel<CinemaDTO> {
		private int cinemaId;
		private String cinemaCode;
		private String cinemaName;
		private String cinemaAddress;
	}

}
