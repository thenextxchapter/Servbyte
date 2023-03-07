package com.nony.servbyte.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends WebApiException{

	public BadRequestException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}

	public BadRequestException() {
		this("Error processing request");
	}
}
