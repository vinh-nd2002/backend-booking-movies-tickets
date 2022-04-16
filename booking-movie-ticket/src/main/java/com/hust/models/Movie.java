package com.hust.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Movies`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class Movie {
	@Column(name = "movie_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;

	@Column(name = "movie_name", length = 100, unique = true, nullable = false)
	private String movieName;

	@Column(name = "movie_description", nullable = false)
	private String movieDescription;

	@Column(name = "movie_trailer", nullable = false)
	private String movieTrailer;

	@Column(name = "movie_release", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date movieRelease;

	@Column(name = "movie_lenght")
	@ColumnDefault("NULL")
	private short movieLenght;

	@Column(name = "movie_poster", nullable = false)
	private String moviePoster;

	@Column(name = "evaluate")
	@ColumnDefault("6")
	private short evaluate;

	@Column(name = "status")
	@ColumnDefault("0")
	private boolean status;
	
	@OneToMany(mappedBy = "ticketOfMovie")
	private List<Ticket> tickets;

}
