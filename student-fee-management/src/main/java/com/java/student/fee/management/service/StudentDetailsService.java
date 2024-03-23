package com.java.student.fee.management.service;

import org.springframework.stereotype.Service;

import com.java.student.fee.management.exception.DuplicateFieldException;
import com.java.student.fee.management.model.StudentDetailsRequest;
import com.java.student.fee.management.model.StudentDetailsResponse;

public interface StudentDetailsService {

	public StudentDetailsResponse addStudentDetails(StudentDetailsRequest studentDetailsRequest)
			throws DuplicateFieldException;

}
