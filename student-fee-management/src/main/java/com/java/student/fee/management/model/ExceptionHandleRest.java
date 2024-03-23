package com.java.student.fee.management.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ExceptionHandleRest {
	
	@Schema(description = "Http status")
	private String statusCode;
	
	@Schema(description = "Error code")
	private String errorCode;
	
	@Schema(description = "Error message")
	private String errorMessage;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
