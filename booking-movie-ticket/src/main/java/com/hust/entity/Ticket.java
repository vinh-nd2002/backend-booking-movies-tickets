package com.hust.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Ticket`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class Ticket {

	@Column(name = "ticket_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketId;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule ticketOfSchedule;

	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seat ticketOfSeat;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie ticketOfMovie;

	@Column(name = "ticket_price", nullable = false)
	private float ticketPrice;
	
	@OneToOne(mappedBy = "billOfTicket")
	private Bill ticketOfBill;
}
