package com.hust.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

	@NonNull
	private String message;

	@NonNull
	private String detailMessage;

	private Object error;

	@NonNull
	private Integer code;

	@NonNull
	private String moreInformation;
}
