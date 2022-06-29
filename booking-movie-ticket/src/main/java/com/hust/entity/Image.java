package com.hust.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private int imgId;

	@Column(name = "img_url")
	private String imgUrl;

	@Column(name = "drive_file_id")
	private String driveFileId;

	@Column(name = "img_type")
	private String imgType;

	@Column(name = "img_name")
	private String imgName;

	@OneToOne
	@JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
	private Movie imgOfMovie;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User imgOfUser;

	@OneToOne
	@JoinColumn(name = "cineplex_id", referencedColumnName = "cineplex_id")
	private Cineplex imgOfCineplex;

}
