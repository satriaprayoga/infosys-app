package com.infosys.webclient.config;

import com.infosys.webclient.exceptions.BadRequestException;
import com.infosys.webclient.exceptions.ResourceNotFoundException;
import com.infosys.webclient.exceptions.UnauthorizedException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class ClientErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		switch (response.status()) {
		case 400:
			return new BadRequestException("bad request");
		case 401:
			return new UnauthorizedException("Unauthorized");
		case 404:
			return new ResourceNotFoundException(Object.class, "Resource Not found","");

		default:
			return new BadRequestException("bad request");
		}
		
	}

}
