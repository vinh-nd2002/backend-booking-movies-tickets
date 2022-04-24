package com.hust.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Ticket`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class Ticket {

	@Column(name = "ticket_code")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "uuid-char")
	private UUID ticketCode;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule ticketsOfSchedule;

	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seat ticketsOfSeat;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie ticketsOfMovie;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account ticketsOfAccount;

	@Column(name = "ticket_price", nullable = false)
	private int ticketPrice;
}
