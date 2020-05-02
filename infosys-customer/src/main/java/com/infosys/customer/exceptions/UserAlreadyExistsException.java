package com.infosys.customer.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7204686826555666438L;
	
	public UserAlreadyExistsException(String msg) {
		super(msg);
	}

}
