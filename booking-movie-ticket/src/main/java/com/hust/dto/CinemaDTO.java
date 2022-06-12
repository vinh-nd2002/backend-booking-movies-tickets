package com.hust.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CinemaDTO extends RepresentationModel<CinemaDTO> {
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

	private CineplexDTO cinemasOfCineplex;

	@Data
	@NoArgsConstructor
	static class CineplexDTO extends RepresentationModel<CineplexDTO> {
		private short cineplexId;

		private String cineplexName;

		@JsonUnwrapped
		private ImageDTO cineplexLogo;

		@Data
		@NoArgsConstructor
		public static class ImageDTO {
//			private int imgId;

			@JsonProperty("cineplexLogo")
			private String imgUrl;
		}
	}
}
