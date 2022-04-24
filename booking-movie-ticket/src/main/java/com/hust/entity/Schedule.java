package com.hust.entity;

import java.util.Date;

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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Schedule`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class Schedule {
	@Column(name = "schedule_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie schedulesOfMovie;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room schedulesOfRoom;

	@Column(name = "schedule_date", nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date scheduleDate;

	@Column(name = "schedule_start", nullable = false, updatable = false)
	@Temporal(TemporalType.TIME)
	private Date scheduleStart;

}
