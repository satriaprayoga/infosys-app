package com.infosys.webclient.exceptions;

public class UnauthorizedException extends RuntimeException{

	private static final long serialVersionUID = -5372492868273037582L;
	
	public UnauthorizedException(String message) {
		super(message);
	}

}
