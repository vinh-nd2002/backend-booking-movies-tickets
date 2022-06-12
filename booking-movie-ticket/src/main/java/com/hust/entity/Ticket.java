package com.hust.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketCode;

	@ManyToOne
	@JoinColumn(name = "schedule_movie_id")
	private ScheduleMovie ticketsOfScheduleMovie;

	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seat ticketsOfSeat;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User ticketsOfUser;

	@Column(name = "ticket_price", nullable = false)
	private int ticketPrice;
	
	@Column(name = "created_date", insertable = false, nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
}
