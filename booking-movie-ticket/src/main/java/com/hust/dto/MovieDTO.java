package com.hust.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDTO {
	private String movieName;
	
	private String movieDescription;

	private String movieTrailer;
	
	private Date movieRelease;
	
	private short movieLenght;
	
	private String moviePoster;
	
	private short evaluate;
	
	private boolean status;
	
}
