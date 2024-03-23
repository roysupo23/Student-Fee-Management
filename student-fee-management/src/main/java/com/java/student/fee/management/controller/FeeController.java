package com.java.student.fee.management.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.java.student.fee.management.exception.DataMismatchException;
import com.java.student.fee.management.exception.NotFoundException;
import com.java.student.fee.management.model.ExceptionHandleRest;
import com.java.student.fee.management.model.FeeRequest;
import com.java.student.fee.management.model.FeeResponse;
import com.java.student.fee.management.service.FeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Fee management", description = "Fee management APIs")
@RestController
@RequestMapping("/fee")
public class FeeController {

	Logger logger = LoggerFactory.getLogger(FeeController.class);

	@Autowired
	FeeService feeService;

	@Operation(
	        summary = "Collect fee of a student for multiple category",
	        description = "Collect fees of a stident from a particular school after validating the details provide by user. "
	        		+ "Fees can be of multiple category like tution fees, sports fee, development fees etc etc.",
	        tags = { "Fee management" },
	        responses = {
	            @ApiResponse(
	                description = "Success",
	                responseCode = "200",
	                content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeeResponse.class))
	            ),
	            @ApiResponse(
	            	description = "Student not found", 
	            	responseCode = "404", 
	            	content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandleRest.class))
	            ),
	            @ApiResponse(
		            	description = "Student data mismatch", 
		            	responseCode = "400", 
		            	content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandleRest.class))
		        ),
	            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	        }
	    )
	@PostMapping(value = "/collect", produces = "application/json", consumes = "application/json")
	public ResponseEntity<FeeResponse> collectFee(@RequestBody @Valid FeeRequest feeRequest)
			throws NotFoundException, DataMismatchException {

		logger.info("Entering collectFee of {}", this.getClass().getSimpleName());

		FeeResponse feeResponse = feeService.collectFee(feeRequest);

		logger.info("Exiting collectFee of {}", this.getClass().getSimpleName());
		return new ResponseEntity<>(feeResponse, HttpStatus.OK);
	}

	@Operation(
	        summary = "Download the fee reciept for a student",
	        description = "Download the pdf of the reciept related to the payment made for a student of a school.",
	        tags = { "Fee management" },
	        responses = {
	            @ApiResponse(
	                description = "Success",
	                responseCode = "200",
	                content = @Content
	            ),
	            @ApiResponse(
	            	description = "Reference number not found", 
	            	responseCode = "404", 
	            	content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandleRest.class))
	            ),
	            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	        }
	    )
	@GetMapping(value = "/pdf/receipt/{referenceNo}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<InputStreamResource> downloadReciept(@PathVariable String referenceNo)
			throws DocumentException, IOException, NotFoundException {

		String fileName = "pdf_" + referenceNo + ".pdf";

		InputStreamResource resource = feeService.downloadReciept(referenceNo, fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/pdf"))
				.body(resource);
	}

	
	@Operation(
	        summary = "Retreive the data of fee reciept for a student",
	        description = "Retreive the data of the reciept related to the payment made for a student of a school.",
	        tags = { "Fee management" },
	        responses = {
	            @ApiResponse(
	                description = "Success",
	                responseCode = "200",
	                content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeeResponse.class))
	            ),
	            @ApiResponse(
		            	description = "Reference number not found", 
		            	responseCode = "404", 
		            	content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandleRest.class))
		        ),
	            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	        }
	    )
	@GetMapping(value = "/data/receipt/{referenceNo}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<FeeResponse> viewReciept(@PathVariable String referenceNo)
			throws DocumentException, IOException, NotFoundException {

		FeeResponse feeResponse = feeService.viewReciept(referenceNo);
		return new ResponseEntity<>(feeResponse, HttpStatus.OK);
	}

}
