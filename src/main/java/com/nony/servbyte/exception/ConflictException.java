package com.nony.servbyte.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends WebApiException{

	public ConflictException(String message) {
		super(message, HttpStatus.CONFLICT);
	}
}
