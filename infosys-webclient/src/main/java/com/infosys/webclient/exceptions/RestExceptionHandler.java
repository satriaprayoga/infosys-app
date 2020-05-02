package com.infosys.webclient.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(BAD_REQUEST, error, ex));
	}
	
	

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		ApiError apiError = new ApiError(BAD_REQUEST);
		apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(
            ResourceNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
	
	@ExceptionHandler(ResourceConstraintException.class)
    protected ResponseEntity<Object> handleConstraintException(
            ResourceConstraintException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
	
	
	@ExceptionHandler(BadRequestException.class)
	protected ResponseEntity<Object> handleBadRequestException(
            BadRequestException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
	
	@ExceptionHandler(UnauthorizedException.class)
	protected ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex){
		ApiError apiError=new ApiError(UNAUTHORIZED);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
