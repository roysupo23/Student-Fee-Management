package com.java.student.fee.management.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.java.student.fee.management.common.ErrorCode;
import com.java.student.fee.management.mapper.MobileNumberDeserializer;

import jakarta.validation.constraints.NotNull;



/**
 * 
 * Not using lombok for not knowing the environment setup. 
 * Not sure if lombok is configured in the environment or not. 
 * So using simple getter setter equals and hash code method
 * 
 */

public class StudentDetailsRequest implements Serializable {

	private static final long serialVersionUID = -4890279193599522835L;
	
	@NotNull(message = ErrorCode.EMPTY_NAME)
	private String name;
	
	@NotNull(message = ErrorCode.EMPTY_GRADE)
	private String grade;
	
	@NotNull(message = ErrorCode.EMPTY_MOBILE_NUMBER)
	@JsonDeserialize(using = MobileNumberDeserializer.class) 
	private String mobileNumber;
	
	@NotNull(message = ErrorCode.EMPTY_SCHOOL_NAME)
	private String schoolName;
	
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
	@Override
	public int hashCode() {
		return Objects.hash(grade, /*id,*/ mobileNumber, name, schoolName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDetailsRequest other = (StudentDetailsRequest) obj;
		return Objects.equals(grade, other.grade) && Objects.equals(mobileNumber, other.mobileNumber)
				&& Objects.equals(name, other.name) && Objects.equals(schoolName, other.schoolName);
	}
	@Override
	public String toString() {
		return "StudentDetailsRequest ["
				+ "name=" + name + ", grade=" + grade + ", mobileNumber="
				+ mobileNumber + ", schoolName=" + schoolName + "]";
	}
	
	

}
