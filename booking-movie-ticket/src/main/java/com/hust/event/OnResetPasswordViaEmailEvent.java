package com.hust.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Khai báo sự kiện reset password
public class OnResetPasswordViaEmailEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private String email;

	public OnResetPasswordViaEmailEvent(String email) {
		super(email);
		this.email = email;
	}

}
