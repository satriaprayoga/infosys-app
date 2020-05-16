package com.infosys.search.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -5298619265957956964L;
	
	public ResourceNotFoundException(String resource, String message) {
		super("Can't found requested resource(s): "+resource+ ", message: "+message);
	}

}
