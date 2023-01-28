package com.nony.servbyte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{
	private static String ERROR_MESSAGE =
			"It looks like this user doesn't exist";

	public UserNotFoundException() {
		super(ERROR_MESSAGE);
	}
}
