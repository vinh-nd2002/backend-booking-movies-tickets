package com.hust.dto;

import com.hust.entity.Seat.SeatType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeatDTO {

	private int seatId;

	private SeatType seatType;

	private short seatNumber;
}
