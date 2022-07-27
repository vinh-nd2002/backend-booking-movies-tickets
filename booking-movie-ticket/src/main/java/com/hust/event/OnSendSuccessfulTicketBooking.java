package com.hust.event;

import org.springframework.context.ApplicationEvent;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class OnSendSuccessfulTicketBooking extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private String email;

	private int scheduleMovieId;

	public OnSendSuccessfulTicketBooking(String email, int scheduleMovieId) {
		super(email);
		this.email = email;
		this.scheduleMovieId = scheduleMovieId;
	}

}
