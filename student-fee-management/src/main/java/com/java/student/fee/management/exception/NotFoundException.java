package com.java.student.fee.management.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -5756763611015474367L;
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(String message, String placeholder) {
		super(String.format(message, placeholder));
	}

}
