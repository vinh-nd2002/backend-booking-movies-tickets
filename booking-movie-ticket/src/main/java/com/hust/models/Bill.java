package com.hust.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@OneToOne
	@JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
	private Ticket billOfTicket;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User billOfUser;
	
	@Column(name = "total", nullable = false)
	private float total;
	
	@Column(name = "created_date", insertable = false, nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;
	
	
	
}
