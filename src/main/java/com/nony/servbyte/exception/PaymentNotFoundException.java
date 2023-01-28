package com.nony.servbyte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends Exception{
	private static String ERROR_MESSAGE =
			"This payment cannot be found";

	public PaymentNotFoundException() {
		super(ERROR_MESSAGE);
	}
}
