package com.realestate.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by trodrigues on 26/06/16.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends ApplicationException {
	public BadRequestException(String message) {
		super(message);
	}
}
