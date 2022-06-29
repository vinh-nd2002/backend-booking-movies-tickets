package com.hust.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`ScheduleMovie`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class ScheduleMovie {
	@Column(name = "schedule_movie_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleMovieId;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@OneToMany(mappedBy = "ticketsOfScheduleMovie")
	private List<Ticket> tickets;

	@Column(name = "schedule_date", nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date scheduleDate;

}
