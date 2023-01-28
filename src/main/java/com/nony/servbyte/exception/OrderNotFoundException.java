package com.nony.servbyte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends Exception{
	private static String ERROR_MESSAGE =
			"Apparently, this order does not exist";

	public OrderNotFoundException() {
		super(ERROR_MESSAGE);
	}
}
