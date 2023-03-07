package com.nony.servbyte.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WebApiException extends RuntimeException{
	protected HttpStatus httpStatus;

	public WebApiException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public WebApiException(String message) {
		this(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
