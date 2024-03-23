package com.java.student.fee.management;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentFeeManagementApplication {
	
	static Logger logger = LoggerFactory.getLogger(StudentFeeManagementApplication.class);
	
	public static void main(String[] args) {
		
		logger.info("StudentFeeManagementApplication started");
		
		SpringApplication.run(StudentFeeManagementApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
