package com.java.student.fee.management.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import com.java.student.fee.management.entity.StudentDetailsEntity;
import com.java.student.fee.management.exception.DuplicateFieldException;
import com.java.student.fee.management.model.StudentDetailsRequest;
import com.java.student.fee.management.model.StudentDetailsResponse;
import com.java.student.fee.management.repo.StudentDetailsRepo;
import com.java.student.fee.management.service.impl.StudentDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
class StudentDetailsServiceTest {

	@InjectMocks
	private StudentDetailsServiceImpl studentDetailsServiceImpl;

	@Spy
	private ModelMapper mockMapper;

	@Mock
	private StudentDetailsRepo mockStudentDetailsRepo;

	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(studentDetailsServiceImpl, "mapper", mockMapper);
		ReflectionTestUtils.setField(studentDetailsServiceImpl, "studentDetailsRepo", mockStudentDetailsRepo);
	}

	@Test
	void testAddStudentDetails() throws Exception {

		Mockito.when(mockStudentDetailsRepo.countMobileNumber("+971 501234567", "Sharaf Aboobacker")).thenReturn(0);

		StudentDetailsRequest studentDetailsRequest = new StudentDetailsRequest();
		studentDetailsRequest.setGrade("3");
		studentDetailsRequest.setName("Sharaf Aboobacker");
		studentDetailsRequest.setMobileNumber("+971 501234567");
		studentDetailsRequest.setSchoolName("Skipy School of Excellence - Integrate model");

		StudentDetailsEntity studentDetailsEntityInput = new StudentDetailsEntity();
		studentDetailsEntityInput.setGrade("3");
		studentDetailsEntityInput.setName("Sharaf Aboobacker");
		studentDetailsEntityInput.setMobileNumber("+971 501234567");
		studentDetailsEntityInput.setSchoolName("Skipy School of Excellence - Integrate model");

		StudentDetailsEntity studentDetailsEntityOutput = new StudentDetailsEntity();
		studentDetailsEntityOutput.setId(1L);
		studentDetailsEntityOutput.setGrade("3");
		studentDetailsEntityOutput.setName("Sharaf Aboobacker");
		studentDetailsEntityOutput.setMobileNumber("+971 501234567");
		studentDetailsEntityOutput.setSchoolName("Skipy School of Excellence - Integrate model");

		StudentDetailsResponse studentDetailsResponse = new StudentDetailsResponse();
		studentDetailsResponse.setId(1L);
		studentDetailsResponse.setGrade("3");
		studentDetailsResponse.setMessage("success");
		studentDetailsResponse.setName("Sharaf Aboobacker");
		studentDetailsResponse.setMobileNumber("+971 501234567");
		studentDetailsResponse.setSchoolName("Skipy School of Excellence - Integrate model");
		
		lenient().when(mockStudentDetailsRepo.save(studentDetailsEntityInput)).thenReturn(studentDetailsEntityOutput);

		assertThat(studentDetailsResponse).usingRecursiveComparison()
				.isEqualTo(studentDetailsServiceImpl.addStudentDetails(studentDetailsRequest));

	}
	
	@Test
	void testAddStudentDetailsException() throws Exception {

		Mockito.when(mockStudentDetailsRepo.countMobileNumber("+971 501234567", "Sharaf Aboobacker")).thenReturn(1);

		StudentDetailsRequest studentDetailsRequest = new StudentDetailsRequest();
		studentDetailsRequest.setGrade("3");
		studentDetailsRequest.setName("Sharaf Aboobacker");
		studentDetailsRequest.setMobileNumber("+971 501234567");
		studentDetailsRequest.setSchoolName("Skipy School of Excellence - Integrate model");
		
		DuplicateFieldException exception = Assertions.assertThrows(DuplicateFieldException.class,
				() -> studentDetailsServiceImpl.addStudentDetails(studentDetailsRequest));
		Assertions.assertEquals("Mobile number already registered. Please use another number for registration.", exception.getMessage());

	}
}
