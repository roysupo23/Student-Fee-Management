package com.java.student.fee.management.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import com.java.student.fee.management.entity.FeeEntity;
import com.java.student.fee.management.entity.StudentDetailsEntity;
import com.java.student.fee.management.exception.DataMismatchException;
import com.java.student.fee.management.exception.NotFoundException;
import com.java.student.fee.management.model.FeeRequest;
import com.java.student.fee.management.model.FeeResponse;
import com.java.student.fee.management.model.FeesType;
import com.java.student.fee.management.repo.FeeRepo;
import com.java.student.fee.management.repo.StudentDetailsRepo;
import com.java.student.fee.management.service.impl.FeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class FeeServiceTest {

	@InjectMocks
	private FeeServiceImpl feeServiceImpl;

	@Spy
	private ModelMapper mockMapper;

	@Mock
	private FeeRepo mockFeeRepo;

	@Mock
	private StudentDetailsRepo mockStudentDetailsRepo;

	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(feeServiceImpl, "mapper", mockMapper);
		ReflectionTestUtils.setField(feeServiceImpl, "feeRepo", mockFeeRepo);
		ReflectionTestUtils.setField(feeServiceImpl, "studentDetailsRepo", mockStudentDetailsRepo);
	}

	@Test
	void testCollectFee() throws NotFoundException, DataMismatchException {
		
		StudentDetailsEntity studentDetailsEntity = new StudentDetailsEntity();
		studentDetailsEntity.setId(1L);
		studentDetailsEntity.setGrade("3");
		studentDetailsEntity.setName("Sharaf Aboobacker");
		studentDetailsEntity.setMobileNumber("+971 501234567");
		studentDetailsEntity.setSchoolName("Skipy School of Excellence - Integrate model");

		FeeRequest feeRequest = new FeeRequest();
		feeRequest.setStudentId(1L);
		feeRequest.setCardType("VISA");
		feeRequest.setCardNo("4321567812346789");
		feeRequest.setPayee("Sharafudeen Aboobacker");
		feeRequest.setStudentName("Sharaf Aboobacker");

		List<FeesType> feesTypeList = new ArrayList<>();

		FeesType feesType = new FeesType();
		
		feesType.setCount(1);
		feesType.setCurrency("AED");
		feesType.setAmount(10000.00);
		feesType.setCategory("tution fees");

		feesTypeList.add(feesType);

		feesType.setCount(1);
		feesType.setAmount(2000.00);
		feesType.setCurrency("AED");
		feesType.setCategory("sport fees");

		feesTypeList.add(feesType);

		feesType.setCount(1);
		feesType.setAmount(500.00);
		feesType.setCurrency("AED");
		feesType.setCategory("development fees");

		feesTypeList.add(feesType);

		feeRequest.setFeetypeList(feesTypeList);
		
		List<FeeEntity> feeEntityInputList = new ArrayList<>();
		List<FeeEntity> feeEntityOutputList = new ArrayList<>();
		
		FeeEntity feeEntity = new FeeEntity();
		
		feeEntity.setCount(1);
		feeEntity.setStudentId(1L);
		feeEntity.setCurrency("AED");
		feeEntity.setCardType("VISA");
		feeEntity.setAmount(10000.00);
		feeEntity.setCategory("tution fees");
		feeEntity.setCardNo("4321567812346789");
		feeEntity.setPayee("Sharafudeen Aboobacker");
		feeEntity.setStudentName("Sharaf Aboobacker");
		feeEntity.setReferenceNo(UUID.randomUUID().toString());
		feeEntity.setSchoolName("Skipy School of Excellence - Integrate model");
		
		feeEntityInputList.add(feeEntity);
		
		feeEntity = new FeeEntity();
		feeEntity.setCount(1);
		feeEntity.setStudentId(1L);
		feeEntity.setCurrency("AED");
		feeEntity.setAmount(2000.00);
		feeEntity.setCardType("VISA");
		feeEntity.setCategory("sport fees");
		feeEntity.setCardNo("4321567812346789");
		feeEntity.setPayee("Sharafudeen Aboobacker");
		feeEntity.setStudentName("Sharaf Aboobacker");
		feeEntity.setReferenceNo(UUID.randomUUID().toString());
		feeEntity.setSchoolName("Skipy School of Excellence - Integrate model");
		
		feeEntityInputList.add(feeEntity);
		
		feeEntity = new FeeEntity();
		feeEntity.setCount(1);
		feeEntity.setStudentId(1L);
		feeEntity.setAmount(500.00);
		feeEntity.setCurrency("AED");
		feeEntity.setCardType("VISA");
		feeEntity.setCardNo("4321567812346789");
		feeEntity.setCategory("development fees");
		feeEntity.setPayee("Sharafudeen Aboobacker");
		feeEntity.setStudentName("Sharaf Aboobacker");
		feeEntity.setReferenceNo(UUID.randomUUID().toString());
		feeEntity.setSchoolName("Skipy School of Excellence - Integrate model");
		
		feeEntityInputList.add(feeEntity);
		
		feeEntity = new FeeEntity();
		
		feeEntity.setId(1L);
		feeEntity.setCount(1);
		feeEntity.setStudentId(1L);
		feeEntity.setCurrency("AED");
		feeEntity.setCardType("VISA");
		feeEntity.setAmount(10000.00);
		feeEntity.setCategory("tution fees");
		feeEntity.setCardNo("4321567812346789");
		feeEntity.setPayee("Sharafudeen Aboobacker");
		feeEntity.setStudentName("Sharaf Aboobacker");
		feeEntity.setReferenceNo(UUID.randomUUID().toString());
		feeEntity.setSchoolName("Skipy School of Excellence - Integrate model");
		
		feeEntityOutputList.add(feeEntity);
		
		feeEntity = new FeeEntity();
		feeEntity.setId(2L);
		feeEntity.setCount(1);
		feeEntity.setStudentId(1L);
		feeEntity.setAmount(2000.00);
		feeEntity.setCurrency("AED");
		feeEntity.setCardType("VISA");
		feeEntity.setCategory("sport fees");
		feeEntity.setCardNo("4321567812346789");
		feeEntity.setPayee("Sharafudeen Aboobacker");
		feeEntity.setStudentName("Sharaf Aboobacker");
		feeEntity.setReferenceNo(UUID.randomUUID().toString());
		feeEntity.setSchoolName("Skipy School of Excellence - Integrate model");

		feeEntityOutputList.add(feeEntity);
		
		feeEntity = new FeeEntity();
		feeEntity.setId(3L);
		feeEntity.setCount(1);
		feeEntity.setStudentId(1L);
		feeEntity.setAmount(500.00);
		feeEntity.setCurrency("AED");
		feeEntity.setCardType("VISA");
		feeEntity.setCardNo("4321567812346789");
		feeEntity.setCategory("development fees");
		feeEntity.setPayee("Sharafudeen Aboobacker");
		feeEntity.setStudentName("Sharaf Aboobacker");
		feeEntity.setReferenceNo(UUID.randomUUID().toString());
		feeEntity.setSchoolName("Skipy School of Excellence - Integrate model");
		
		
		feeEntityOutputList.add(feeEntity);
		
		FeeResponse feeResponse = new FeeResponse();
		
		feeResponse.setStudentId(1L);
		feeResponse.setCardType("VISA");
		feeResponse.setCardNo("4321567812346789");
		feeResponse.setFeesTypeList(feesTypeList);
		feeResponse.setPayee("Sharafudeen Aboobacker");
		feeResponse.setStudentName("Sharaf Aboobacker");
		feeResponse.setSchoolName("Skipy School of Excellence - Integrate model");
		
		Mockito.when(mockStudentDetailsRepo.findById(1L)).thenReturn(Optional.of(studentDetailsEntity));
		
		lenient().when(mockFeeRepo.saveAll(Mockito.anyList())).thenReturn(feeEntityOutputList);

		assertThat(feeResponse).usingRecursiveComparison().ignoringFields("referenceNo", "timestamp")
				.isEqualTo(feeServiceImpl.collectFee(feeRequest));

	}

	@Test
	void testCollectionFeeException1() {

		Mockito.when(mockStudentDetailsRepo.findById(1L)).thenReturn(Optional.empty());

		FeeRequest feeRequest = new FeeRequest();
		feeRequest.setStudentId(1L);
		feeRequest.setCardType("VISA");
		feeRequest.setCardNo("4321567812346789");
		feeRequest.setPayee("Sharafudeen Aboobacker");
		feeRequest.setStudentName("Sharaf Aboobacker");

		List<FeesType> feesTypeList = new ArrayList<>();

		FeesType feesType = new FeesType();
		
		feesType.setCount(1);
		feesType.setCurrency("AED");
		feesType.setAmount(10000.00);
		feesType.setCategory("tution fees");

		feesTypeList.add(feesType);
		
		feesType.setCount(1);
		feesType.setCurrency("AED");
		feesType.setAmount(2000.00);
		feesType.setCategory("sport fees");

		feesTypeList.add(feesType);

		feesType.setCount(1);
		feesType.setCurrency("AED");
		feesType.setAmount(500.00);
		feesType.setCategory("development fees");

		feesTypeList.add(feesType);

		feeRequest.setFeetypeList(feesTypeList);

		NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
				() -> feeServiceImpl.collectFee(feeRequest));
		Assertions.assertEquals("Student details not available in the system.", exception.getMessage());
	}

	@Test
	void testCollectionFeeException2() {

		StudentDetailsEntity studentDetailsEntity = new StudentDetailsEntity();
		studentDetailsEntity.setId(1L);
		studentDetailsEntity.setGrade("3");
		studentDetailsEntity.setName("Sharaf Aboobacker 1");
		studentDetailsEntity.setMobileNumber("+971 501234567");
		studentDetailsEntity.setSchoolName("Skipy School of Excellence - Integrate model");

		Mockito.when(mockStudentDetailsRepo.findById(1L)).thenReturn(Optional.of(studentDetailsEntity));

		FeeRequest feeRequest = new FeeRequest();
		
		feeRequest.setStudentId(1L);
		feeRequest.setCardType("VISA");
		feeRequest.setCardNo("4321567812346789");
		feeRequest.setPayee("Sharafudeen Aboobacker");
		feeRequest.setStudentName("Sharaf Aboobacker");

		List<FeesType> feesTypeList = new ArrayList<>();

		FeesType feesType = new FeesType();
		
		feesType.setCount(1);
		feesType.setCurrency("AED");
		feesType.setAmount(10000.00);
		feesType.setCategory("tution fees");
		
		feesTypeList.add(feesType);

		feesType.setCount(1);
		feesType.setCurrency("AED");
		feesType.setAmount(2000.00);
		feesType.setCategory("sport fees");

		feesTypeList.add(feesType);

		feesType.setCount(1);
		feesType.setCurrency("AED");
		feesType.setAmount(500.00);
		feesType.setCategory("development fees");

		feesTypeList.add(feesType);

		feeRequest.setFeetypeList(feesTypeList);

		DataMismatchException exception = Assertions.assertThrows(DataMismatchException.class,
				() -> feeServiceImpl.collectFee(feeRequest));
		Assertions.assertEquals("Student id and name does not match.", exception.getMessage());
	}
}
