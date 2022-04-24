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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Bill`", catalog = "booking_movie_ticket")
@Data
@NoArgsConstructor
public class Bill {

	@Column(name = "bill_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int billId;

	@OneToMany
	@JoinColumn(name = "ticket_code")
	private List<Ticket> billOfTickets;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account billsOfAccount;

	@Column(name = "total", nullable = false)
	private int total;

	@Column(name = "created_date", insertable = false, nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

}
