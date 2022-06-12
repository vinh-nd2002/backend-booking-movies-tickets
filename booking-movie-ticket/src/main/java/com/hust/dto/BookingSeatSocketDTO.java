package com.hust.dto;

import java.util.List;

import com.hust.entity.Seat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingSeatSocketDTO {
	
	private int scheduleMovieId;

	private List<Seat> seats;

	private String username;

}
