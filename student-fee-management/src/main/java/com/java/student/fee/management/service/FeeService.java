package com.java.student.fee.management.service;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;

import com.itextpdf.text.DocumentException;
import com.java.student.fee.management.exception.DataMismatchException;
import com.java.student.fee.management.exception.NotFoundException;
import com.java.student.fee.management.model.FeeRequest;
import com.java.student.fee.management.model.FeeResponse;

public interface FeeService {

	public FeeResponse collectFee(FeeRequest feeRequest) throws NotFoundException, DataMismatchException;

	public InputStreamResource downloadReciept(String referenceNo, String fileName)
			throws DocumentException, IOException, NotFoundException;

	public FeeResponse viewReciept(String referenceNo) throws DocumentException, IOException, NotFoundException;

}
