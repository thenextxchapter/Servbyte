package com.nony.servbyte.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CityNotFoundException extends Exception{

	private static String ERROR_MESSAGE =
			"Looks like we do not have a restaurant available in this city";

	public CityNotFoundException() {
		super(ERROR_MESSAGE);
	}
}
