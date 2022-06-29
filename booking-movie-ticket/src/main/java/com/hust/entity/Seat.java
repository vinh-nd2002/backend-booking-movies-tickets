package com.hust.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Seat`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class Seat {
	@Column(name = "seat_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatId;

	@Column(name = "seat_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private SeatType seatType;

	@Column(name = "seat_number", nullable = false)
	private short seatNumber;

	public enum SeatType {
		VIP, Thường
	}
}
