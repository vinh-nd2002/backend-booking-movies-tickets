package com.hust.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Image`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class Image {

	@Column(name = "img_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short imgId;

	@Lob
	@Column(name = "img_data")
	private byte[] imgData;

	@Column(name = "img_url")
	private String imgUrl;

	@OneToOne
	@JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
	private Movie imgOfMovie;

	@OneToOne
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account imgOfAccount;

	@OneToOne
	@JoinColumn(name = "cineplex_id", referencedColumnName = "cineplex_id")
	private Cineplex imgOfCineplex;
}
