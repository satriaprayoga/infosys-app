package com.infosys.customer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ActivationKeyExpiredException extends RuntimeException{

	private static final long serialVersionUID = 3674217402607506917L;
	
	public ActivationKeyExpiredException(String message) {
		super(message);
	}

}
