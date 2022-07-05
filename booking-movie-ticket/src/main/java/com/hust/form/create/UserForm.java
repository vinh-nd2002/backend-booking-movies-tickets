package com.hust.form.create;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.hust.entity.User.GenderType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserForm {

	private String username;

	private String email;

	private String numberPhone;

	private String password;

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	@Enumerated(EnumType.STRING)
	private GenderType gender;

	private String address;

}
