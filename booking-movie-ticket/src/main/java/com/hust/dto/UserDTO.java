package com.hust.dto;

import java.util.Date;

import com.hust.entity.User.GenderType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	private int userId;

	private String username;

	private String email;

	private String numberPhone;

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	private GenderType gender;

	private String address;

	private String avatarUrl;
}
