package com.hust.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import com.hust.entity.User.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDTO {

	private int userId;

	private String username;

	private String email;

	private String numberPhone;

	private String firstName;

	private String lastName;

	private Role role;

	@JsonUnwrapped
	private ImageDTO avatar;

	@Data
	@NoArgsConstructor
	public static class ImageDTO {
		private int imgId;

		private String imgUrl;
	}

	private String accessToken;

	private String refreshToken;

}
