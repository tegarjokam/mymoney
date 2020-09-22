package com.mymoney.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -5568638582551830526L;

	private int status;
	
	private String message;
	
	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public ServiceException(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

}
