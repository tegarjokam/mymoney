package com.mymoney.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler 
	extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse  exceptionResponse = new ExceptionResponse(new Date(), 500, "Internal Server Error", ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/**
	 * Exception
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	public final ResponseEntity<Object> ServiceException(ServiceException ex, WebRequest request) {
		ExceptionResponse  exceptionResponse = new ExceptionResponse(new Date(), ex.getStatus(), ex.getMessage(), ex.getMessage(), request.getDescription(false));
		
		if (ex.getStatus() == 404) {
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
		} else if (ex.getStatus() == 409) {
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.CONFLICT);
		} else if (ex.getStatus() == 400) {
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
