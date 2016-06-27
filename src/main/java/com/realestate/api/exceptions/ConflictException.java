package com.realestate.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by trodrigues on 26/06/16.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends ApplicationException {
	private static final long serialVersionUID = -32345195598798712L;

	public ConflictException(String message) {
		super(message);
	}
}
