package com.java.student.fee.management.exception;

public class DataMismatchException extends Exception {

	private static final long serialVersionUID = -5275497308188566927L;

	public DataMismatchException(String message) {
		super(message);
	}
	
	public DataMismatchException(String message, String placeholder) {
		super(String.format(message, placeholder));
	}
}
