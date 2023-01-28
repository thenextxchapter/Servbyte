package com.nony.servbyte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderItemNotFoundException extends Exception{
	private static String ERROR_MESSAGE =
			"It looks like the order item you're looking for doesn't exist";

	public OrderItemNotFoundException() {
		super(ERROR_MESSAGE);
	}
}
