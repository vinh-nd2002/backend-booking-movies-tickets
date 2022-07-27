package com.hust.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import com.hust.entity.Ticket.Status;
import com.hust.entity.User.GenderType;
import com.hust.entity.User.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {

	private int userId;

	private String email;

	private String numberPhone;

	private String firstName;

	private String lastName;

	private GenderType gender;

	private String address;

	private Role role;

	@JsonUnwrapped
	private ImageDTO avatar;

	@Data
	@NoArgsConstructor
	public static class ImageDTO {

		private String imgUrl;
	}
	
	private List<TicketDTO> tickets;

	@Data
	@NoArgsConstructor
	public static class TicketDTO {
		private int ticketId;

		private String ticketCode;

		private Status ticketStatus;

		private int ticketPrice;
	}


}
