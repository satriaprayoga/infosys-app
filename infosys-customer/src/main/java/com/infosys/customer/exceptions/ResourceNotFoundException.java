package com.infosys.customer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 923501814328378550L;
	
	public ResourceNotFoundException(Class<?> clazz, String... searchParamsMap) {
        super(clazz.getSimpleName()+"with params : "+searchParamsMap+" not found");
    }

    

}
