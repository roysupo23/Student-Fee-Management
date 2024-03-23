package com.java.student.fee.management.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class StudentDetailsResponse implements Serializable{

	private static final long serialVersionUID = 4111040912186819746L;
	
	@Schema(name = "Student ID")
	private Long id;
	
	@Schema(name = "Student name")
	private String name;
	
	@Schema(name = "Grade of the student")
	private String grade;
	
	@Schema(name = "Parent mobile number")
	private String mobileNumber;
	
	@Schema(name = "School name")
	private String schoolName;
	
	@Schema(name = "Student name")
	private String message;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
