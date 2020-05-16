package com.infosys.customer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceConstraintException extends RuntimeException {

	
	private static final long serialVersionUID = -2571913541893966353L;
	

	public ResourceConstraintException(Class<?> clazz, String... searchParamsMap) {
		super(clazz.getSimpleName()+" with params: "+searchParamsMap+" might be connected to other entities");
	}
	
	  
}
