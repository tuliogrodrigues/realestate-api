package com.realestate.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by trodrigues on 26/06/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ApplicationException {
	private static final long serialVersionUID = -4860094494233778276L;

	public NotFoundException(String message) {
		super(message);
	}
}
