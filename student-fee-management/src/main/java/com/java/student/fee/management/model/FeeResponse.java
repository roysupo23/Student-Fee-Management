package com.java.student.fee.management.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.java.student.fee.management.mapper.DateDeserializer;

import io.swagger.v3.oas.annotations.media.Schema;

public class FeeResponse implements Serializable {

	private static final long serialVersionUID = 1270392053172138355L;

	@Schema(name = "Student ID")
	private Long studentId;
	
	@Schema(name = "Student name")
	private String studentName;
	
	@Schema(name = "Payee name")
	private String payee;
	
	@Schema(name = "Card number")
	private String cardNo;
	
	@Schema(description = "Card type")
	private String cardType;
	
	@Schema(description = "Reference number")
	private String referenceNo;
	
	@JsonDeserialize(using = DateDeserializer.class) 
	private String timestamp;
	
	private String schoolName;
	private List<FeesType> feesTypeList;
	
	
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public List<FeesType> getFeesTypeList() {
		return feesTypeList;
	}
	public void setFeesTypeList(List<FeesType> feesTypeList) {
		this.feesTypeList = feesTypeList;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cardNo, cardType, feesTypeList, payee, referenceNo, schoolName, studentId, studentName,
				timestamp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeeResponse other = (FeeResponse) obj;
		return Objects.equals(cardNo, other.cardNo) && Objects.equals(cardType, other.cardType)
				&& Objects.equals(feesTypeList, other.feesTypeList) && Objects.equals(payee, other.payee)
				&& Objects.equals(referenceNo, other.referenceNo)
				&& Objects.equals(schoolName, other.schoolName) && Objects.equals(studentId, other.studentId)
				&& Objects.equals(studentName, other.studentName) && Objects.equals(timestamp, other.timestamp);
	}
	@Override
	public String toString() {
		return "FeeResponse [studentId=" + studentId + ", studentName=" + studentName + ", payee=" + payee + ", cardNo="
				+ cardNo + ", cardType=" + cardType + ", referenceNo=" + referenceNo + ", timestamp="
				+ timestamp + ", schoolName=" + schoolName + ", feesTypeList=" + feesTypeList + "]";
	}
	
}
