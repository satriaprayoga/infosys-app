package com.infosys.customer.exceptions;

public class ActivationKeyExpiredException extends RuntimeException{

	private static final long serialVersionUID = 3674217402607506917L;
	
	public ActivationKeyExpiredException(String message) {
		super(message);
	}

}
