package com.hust.entity;

import java.io.Serializable;
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
@Table(name = "`Cinema`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class Cinema implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "cinema_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cinemaId;

	@Column(name = "cinema_code", length = 50, unique = true, updatable = false, nullable = false)
	private String cinemaCode;

	@Column(name = "cinema_name", length = 50, unique = true, nullable = false)
	private String cinemaName;

	@Column(name = "cinema_address", length = 100, nullable = false)
	private String cinemaAddress;

	@ManyToOne
	@JoinColumn(name = "cineplex_id")
	private Cineplex cinemasOfCineplex;

	@OneToMany(mappedBy = "roomsOfCinema")
	private List<Room> rooms;
}
