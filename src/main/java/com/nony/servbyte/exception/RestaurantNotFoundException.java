package com.nony.servbyte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends Exception{
	private static String ERROR_MESSAGE =
			"This restaurant does not currently exist";

	public RestaurantNotFoundException() {
		super(ERROR_MESSAGE);
	}
}
