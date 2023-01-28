package com.nony.servbyte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MealNotFoundException extends Exception{
	private static String ERROR_MESSAGE =
			"The meal you're looking for isn't available, unfortunately";

	public MealNotFoundException() {
		super(ERROR_MESSAGE);
	}
}
