package com.hust.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

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
	@Enumerated(EnumType.STRING)
	private Role role = Role.CUSTOMER;

	@Column(name = "created_date", insertable = false, nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@OneToOne(mappedBy = "imgOfUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Image avatar;

	@OneToMany(mappedBy = "ticketsOfUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ticket> tickets;

	@Column(name = "is_active")
	@ColumnDefault("0")
	private boolean isActive;

	@Column(name = "blocked")
	@ColumnDefault("0")
	private boolean blocked;

	public enum GenderType {
		MALE, FEMALE
	}

	public enum Role {
		CUSTOMER, ADMIN
	}

}
