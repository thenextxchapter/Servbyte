package com.nony.servbyte.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends WebApiException{

	public NotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}

	public NotFoundException() {
		super("Not found", HttpStatus.NOT_FOUND);
	}
}
