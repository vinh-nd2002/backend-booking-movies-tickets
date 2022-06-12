package com.hust.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hust.entity.User.GenderType;
import com.hust.entity.User.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {

	private int userId;

	private String username;

	private String email;

	private String numberPhone;

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	private GenderType gender;

	private String address;
	
	private Role role;

	@JsonUnwrapped
	private ImageDTO avatar;

	@Data
	@NoArgsConstructor
	public static class ImageDTO {
		private int imgId;

		private String imgUrl;
	}

}
