package com.hust.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`User`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class User {
	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(name = "username", length = 50, unique = true, updatable = false, nullable = false)
	private String username;

	@Column(name = "email", length = 50, unique = true, nullable = false)
	private String email;

	@Column(name = "number_phone", length = 10, nullable = false)
	private String numberPhone;

	@Column(name = "password", length = 800, nullable = false)
	private String password;

	@Column(name = "first_name", length = 30, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 30, nullable = false)
	private String lastName;

	@Column(name = "date_of_birth", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private GenderType gender;

	@Column(name = "address", length = 100, nullable = false)
	private String address;

	@Column(name = "role", nullable = false)
	@ColumnDefault("Customer")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "created_date", insertable = false, nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@OneToOne(mappedBy = "imgOfUser")
	private Image avatar;

	public enum GenderType {
		MALE, FEMALE
	}

	public enum Role {
		CUSTOMER, ADMIN
	}

}
