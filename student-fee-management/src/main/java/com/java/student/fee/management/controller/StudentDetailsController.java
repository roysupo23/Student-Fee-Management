package com.java.student.fee.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.student.fee.management.exception.DuplicateFieldException;
import com.java.student.fee.management.model.ExceptionHandleRest;
import com.java.student.fee.management.model.StudentDetailsRequest;
import com.java.student.fee.management.model.StudentDetailsResponse;
import com.java.student.fee.management.service.StudentDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Student management", description = "Student management APIs")
@RestController
@RequestMapping("/student")
public class StudentDetailsController {

	Logger logger = LoggerFactory.getLogger(StudentDetailsController.class);

	@Autowired
	StudentDetailsService studentService;
	
	@Operation(
	        summary = "Onboarding student of a school",
	        description = "Onboard students from different school for the purpose of collecting fees deposited by any payee.",
	        tags = { "Onboard Student" },
	        responses = {
	            @ApiResponse(
	                description = "Success",
	                responseCode = "200",
	                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDetailsResponse.class))
	            ),
	            @ApiResponse(
	            	description = "Duplicate mobile number", 
	            	responseCode = "400", 
	            	content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandleRest.class))
	            )	,
	            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	        }
	    )
	@PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
	public ResponseEntity<StudentDetailsResponse> addStudentDetails(@RequestBody @Valid StudentDetailsRequest request)
			throws DuplicateFieldException {

		logger.info("Entering addStudentDetails of class {}", this.getClass().getSimpleName());

		StudentDetailsResponse response = studentService.addStudentDetails(request);

		logger.info("Exiting addStudentDetails of class {}", this.getClass().getSimpleName());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
