package com.infosys.webclient.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 5776342166557337192L;

	public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
