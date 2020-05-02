package com.infosys.webclient.exceptions;

public class UserAlreadyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistException(String email) {
		super(String.format("User with email %s already exist", email));
	}

}
