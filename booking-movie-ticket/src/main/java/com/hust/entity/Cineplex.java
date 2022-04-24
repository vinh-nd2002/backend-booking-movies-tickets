package com.hust.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Cineplex`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class Cineplex {

	@Column(name = "cineplex_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short cineplexId;

	@Column(name = "cineplex_code", length = 20, unique = true, updatable = false, nullable = false)
	private String cineplexCode;

	@Column(name = "cineplex_name", length = 50, unique = true, nullable = false)
	private String cineplexName;

	@OneToMany(mappedBy = "cinemasOfCineplex")
	private List<Cinema> cinemas;

	@OneToOne(mappedBy = "imgOfCineplex")
	private Image cineplexLogo;
}
