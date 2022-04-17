package com.hust.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Room`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class Room {
	@Column(name = "room_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;

	@Column(name = "room_name", length = 20, nullable = false)
	private String roomName;

	@ManyToOne
	@JoinColumn(name = "cinema_id")
	private Cinema roomOfCinema;

	@OneToMany(mappedBy = "scheduleOfRoom")
	private List<Schedule> schedules;

	@OneToMany(mappedBy = "seatOfRoom")
	private List<Seat> seats;

}
