package com.hust.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.hust.entity.Account.GenderType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO extends RepresentationModel<AccountDTO> {

	private int accountId;

	private String username;

	private String email;

	private String numberPhone;

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	private GenderType gender;

	private String address;

	private ImageDTO avatar;

	@Data
	@NoArgsConstructor
	public static class ImageDTO {
		private int imgId;

		private String imgUrl;
	}

}
