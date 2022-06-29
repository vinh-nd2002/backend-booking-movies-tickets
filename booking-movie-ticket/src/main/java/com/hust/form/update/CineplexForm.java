package com.hust.form.update;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CineplexForm {

	private short cineplexId;

	private String cineplexCode;

	private String cineplexName;

	private List<Cinema> cinemas;

	@Data
	@NoArgsConstructor
	public static class Cinema {

		private short cinemaId;

		private String cinemaCode;

		private String cinemaName;

		private String cinemaAddress;
	}

}
