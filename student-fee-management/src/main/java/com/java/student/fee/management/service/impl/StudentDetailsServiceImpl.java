package com.java.student.fee.management.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.student.fee.management.common.Constant;
import com.java.student.fee.management.common.ErrorCode;
import com.java.student.fee.management.entity.StudentDetailsEntity;
import com.java.student.fee.management.exception.DuplicateFieldException;
import com.java.student.fee.management.model.StudentDetailsRequest;
import com.java.student.fee.management.model.StudentDetailsResponse;
import com.java.student.fee.management.repo.StudentDetailsRepo;
import com.java.student.fee.management.service.StudentDetailsService;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

	Logger logger = LoggerFactory.getLogger(StudentDetailsServiceImpl.class);

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private StudentDetailsRepo studentDetailsRepo;

	@Transactional
	@Override
	public StudentDetailsResponse addStudentDetails(StudentDetailsRequest studentDetailsRequest)
			throws DuplicateFieldException {

		logger.info("Entering addStudentDetails of class {}", this.getClass().getSimpleName());

		Integer mobileNumberCount = studentDetailsRepo.countMobileNumber(studentDetailsRequest.getMobileNumber(),
				studentDetailsRequest.getName());
		if (mobileNumberCount > 0) {
			throw new DuplicateFieldException(ErrorCode.DUPLICATE_MOBILE_NUMBER);
		}

		StudentDetailsEntity studentDetailsEntity = mapper.map(studentDetailsRequest, StudentDetailsEntity.class);
		studentDetailsEntity = studentDetailsRepo.save(studentDetailsEntity);
		StudentDetailsResponse studentDetailsResponse = mapper.map(studentDetailsEntity, StudentDetailsResponse.class);
		studentDetailsResponse.setMessage(Constant.SUCCESS);

		logger.info("Exiting addStudentDetails of class {}", this.getClass().getSimpleName());
		return studentDetailsResponse;
	}

}
