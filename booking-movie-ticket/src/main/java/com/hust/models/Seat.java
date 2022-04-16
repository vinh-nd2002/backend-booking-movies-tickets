package com.hust.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

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

	@Column(name = "seat_status", nullable = false)
	@ColumnDefault("0")
	private boolean seatStatus;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room seatOfRoom;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User seatOfuser;
	
	@OneToMany(mappedBy = "ticketOfSeat")
	private List<Ticket> tickets;

	public enum SeatType {
		VIP, Thường
	}
}
