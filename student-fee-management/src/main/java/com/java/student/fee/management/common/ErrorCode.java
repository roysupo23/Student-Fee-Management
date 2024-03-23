package com.java.student.fee.management.common;

public class ErrorCode {
	
	private ErrorCode() {}
	
	public static final String EMPTY_NAME = "Student Name should not be empty.";
	public static final String EMPTY_GRADE = "Student grade should not be empty.";
	public static final String EMPTY_MOBILE_NUMBER = "Mobile number should not be empty.";
	public static final String EMPTY_SCHOOL_NAME = "School name should not be empty.";
	public static final String INVALID_MOBILE_NUMBER = "Mobile number can contain only number and ( ) + -";
	
	public static final String EMPTY_STUDENT_ID = "Student ID should not be empty.";
	public static final String EMPTY_PAYEE = "Payee name should not be empty.";
	public static final String EMPTY_AMOUNT = "Amount should not be empty.";
	public static final String EMPTY_CURRENCY = "Currency should not be empty.";
	public static final String EMPTY_CARD_NUMBER = "Card number should not be empty.";
	public static final String EMPTY_FEE_TYPE = "Please provide at least one fee type.";
	public static final String INVALID_CARD_SIZE = "Invalid card number.";
	public static final String INVALID_CARD_NUMBER = "Card number can be only numeric.";
	
	public static final String DUPLICATE_MOBILE_NUMBER = "Mobile number already registered. Please use another number for registration.";
	
	public static final String STUDENT_NOT_FOUND = "Student details not available in the system.";
	public static final String STUDENT_DATA_MISMATCH = "Student id and name does not match.";
	public static final String INVALID_REFERENCE_NUMBER = "Details not available for reference number %s.";
	
	public static final String GENERAL_ERROR_MESSAGE = "Unable to proceed with the request. Please try after some time.";

}
