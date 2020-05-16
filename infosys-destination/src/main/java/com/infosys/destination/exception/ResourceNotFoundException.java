package com.infosys.destination.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 4820331907061567324L;

	
	public ResourceNotFoundException(Class<?> clz, String params) {
		super(clz.getSimpleName()+" with params "+params+", Not Found");
	}
}
