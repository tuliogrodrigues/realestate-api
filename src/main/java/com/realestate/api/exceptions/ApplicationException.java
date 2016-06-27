package com.realestate.api.exceptions;

import java.util.function.Supplier;

/**
 * Created by trodrigues on 26/06/16.
 */
class ApplicationException extends RuntimeException implements Supplier<RuntimeException> {

	private static final long serialVersionUID = 1046181463183760954L;

	ApplicationException(String message) {
		super(message);
	}

	@Override
	public RuntimeException get() {
		return this;
	}
}
