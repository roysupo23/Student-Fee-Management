package com.java.student.fee.management.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.java.student.fee.management.common.ErrorCode;
import com.java.student.fee.management.mapper.CardNumberDeserializer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FeeRequest implements Serializable{

	private static final long serialVersionUID = 5993435158623329543L;
	
	@NotNull(message = ErrorCode.EMPTY_STUDENT_ID)
	private Long studentId;
	
	@NotNull(message = ErrorCode.EMPTY_NAME)
	private String studentName;
	
	@NotNull(message = ErrorCode.EMPTY_PAYEE)
	private String payee;
	
	@NotNull(message = ErrorCode.EMPTY_FEE_TYPE)
	private List<FeesType> feetypeList;
	
	@Size(min=16,message=ErrorCode.INVALID_CARD_SIZE)
	@NotNull(message = ErrorCode.EMPTY_CARD_NUMBER)
	@JsonDeserialize(using = CardNumberDeserializer.class) 
	private String cardNo;
	
	@NotNull(message = ErrorCode.EMPTY_NAME)
	private String cardType;
	
	public Long getStudentId() {
		return studentId;
	}
	public List<FeesType> getFeetypeList() {
		return feetypeList;
	}
	public void setFeetypeList(List<FeesType> feetypeList) {
		this.feetypeList = feetypeList;
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
	@Override
	public int hashCode() {
		return Objects.hash(cardNo, cardType, feetypeList, payee, studentId, studentName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeeRequest other = (FeeRequest) obj;
		return Objects.equals(cardNo, other.cardNo) && Objects.equals(cardType, other.cardType)
				&& Objects.equals(feetypeList, other.feetypeList) && Objects.equals(payee, other.payee)
				&& Objects.equals(studentId, other.studentId) && Objects.equals(studentName, other.studentName);
	}
	@Override
	public String toString() {
		return "FeeRequest [studentId=" + studentId + ", studentName=" + studentName + ", payee=" + payee
				+ ", feetypeList=" + feetypeList + ", cardNo=" + cardNo + ", cardType=" + cardType + "]";
	}
	

}
