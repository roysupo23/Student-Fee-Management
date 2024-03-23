package com.java.student.fee.management.exception;

public class DuplicateFieldException extends Exception {

	private static final long serialVersionUID = -5252014286946553298L;
	
	public DuplicateFieldException(String message) {
		super(message);
	}
	
	public DuplicateFieldException(String message, String placeholder) {
		super(String.format(message, placeholder));
	}

}
