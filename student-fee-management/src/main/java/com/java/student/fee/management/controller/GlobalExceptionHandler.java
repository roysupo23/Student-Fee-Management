package com.java.student.fee.management.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.itextpdf.text.DocumentException;
import com.java.student.fee.management.common.ErrorCode;
import com.java.student.fee.management.exception.DataMismatchException;
import com.java.student.fee.management.exception.DuplicateFieldException;
import com.java.student.fee.management.exception.NotFoundException;
import com.java.student.fee.management.model.ExceptionHandleRest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	private static final String EXCEPTION_MESSAGE = "Exception occured. Exception message: {}";

	@ExceptionHandler(DuplicateFieldException.class)
	protected ResponseEntity<Object> handleDuplicateFieldException(DuplicateFieldException ex) {
		
		logger.info(EXCEPTION_MESSAGE,ex.getMessage());
		ex.printStackTrace();
		
		ExceptionHandleRest rest = new ExceptionHandleRest();
		rest.setStatusCode("400");
		rest.setErrorMessage(ex.getMessage());
		rest.setErrorCode("DUPLICATE_MOBILE_NUMBER");
		return new ResponseEntity<>(rest, HttpStatus.OK);

	}

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		
		logger.info(EXCEPTION_MESSAGE,ex.getMessage());
		ex.printStackTrace();
		
		ExceptionHandleRest rest = new ExceptionHandleRest();
		rest.setStatusCode("404");
		rest.setErrorMessage(ex.getMessage());
		rest.setErrorCode("DETAILS_NOT_FOUND");
		return new ResponseEntity<>(rest, HttpStatus.OK);
	}

	@ExceptionHandler(DataMismatchException.class)
	protected ResponseEntity<Object> handleDataMismatchException(DataMismatchException ex) {
		
		logger.info(EXCEPTION_MESSAGE,ex.getMessage());
		ex.printStackTrace();
		
		ExceptionHandleRest rest = new ExceptionHandleRest();
		rest.setStatusCode("400");
		rest.setErrorMessage(ex.getMessage());
		rest.setErrorCode("INPUT_DATA_MISMATCH");
		return new ResponseEntity<>(rest, HttpStatus.OK);
	}
	
	@ExceptionHandler(DocumentException.class)
	protected ResponseEntity<Object> handleDocumentException(DocumentException ex) {
		
		logger.info(EXCEPTION_MESSAGE,ex.getMessage());
		ex.printStackTrace();
		
		ExceptionHandleRest rest = new ExceptionHandleRest();
		rest.setStatusCode("500");
		rest.setErrorCode("PDF_GENERATE_EXCEPTION");
		rest.setErrorMessage(ErrorCode.GENERAL_ERROR_MESSAGE);
		return new ResponseEntity<>(rest, HttpStatus.OK);
	}
	
	@ExceptionHandler(IOException.class)
	protected ResponseEntity<Object> handleIOException(IOException ex) {
		
		logger.info(EXCEPTION_MESSAGE,ex.getMessage());
		ex.printStackTrace();
		
		ExceptionHandleRest rest = new ExceptionHandleRest();
		rest.setStatusCode("500");
		rest.setErrorCode("INTERNAL_SERVER_EXCEPTION");
		rest.setErrorMessage(ErrorCode.GENERAL_ERROR_MESSAGE);
		return new ResponseEntity<>(rest, HttpStatus.OK);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		
		logger.info(EXCEPTION_MESSAGE, ex.getFieldError().getDefaultMessage());
		ex.printStackTrace();
		
		ExceptionHandleRest rest = new ExceptionHandleRest();
		rest.setStatusCode("400");
		rest.setErrorMessage(ex.getFieldError().getDefaultMessage());
		rest.setErrorCode("MANDATORY_FEILD_MISSING");
		return new ResponseEntity<>(rest, HttpStatus.OK);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		
		logger.info(EXCEPTION_MESSAGE, ex.getCause().getMessage());
		ex.printStackTrace();
		
		ExceptionHandleRest rest = new ExceptionHandleRest();
		rest.setStatusCode("400");
		rest.setErrorCode("INVALID_INPUT_PARAMETERS");
		rest.setErrorMessage(ex.getCause().getMessage());
		return new ResponseEntity<>(rest, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(Exception ex) {
		
		logger.info(EXCEPTION_MESSAGE,ex.getMessage());
		ex.printStackTrace();
		
		ExceptionHandleRest rest = new ExceptionHandleRest();
		rest.setStatusCode("500");
		rest.setErrorCode("INTERNAL_SERVER_EXCEPTION");
		rest.setErrorMessage(ErrorCode.GENERAL_ERROR_MESSAGE);
		return new ResponseEntity<>(rest, HttpStatus.OK);
	}

}
