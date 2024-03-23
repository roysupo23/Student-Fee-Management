package com.java.student.fee.management.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "STUDENT_DETAILS")
public class StudentDetailsEntity implements Serializable{

	private static final long serialVersionUID = -1614002469605605377L;
	
	@Id
    @Column(name="STUDENT_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="STUDENT_NAME")
	private String name;
	
	@Column(name="GRADE")
	private String grade;
	
	@Column(name="MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name="SCHOOL_NAME")
	private String schoolName;
	
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
	@Override
	public int hashCode() {
		return Objects.hash(grade, id, mobileNumber, name, schoolName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDetailsEntity other = (StudentDetailsEntity) obj;
		return Objects.equals(grade, other.grade) && id == other.id && Objects.equals(mobileNumber, other.mobileNumber)
				&& Objects.equals(name, other.name) && Objects.equals(schoolName, other.schoolName);
	}
	@Override
	public String toString() {
		return "StudentDetailsEntity [id=" + id + ", name=" + name + ", grade=" + grade + ", mobileNumber="
				+ mobileNumber + ", schoolName=" + schoolName + "]";
	}
	
}
