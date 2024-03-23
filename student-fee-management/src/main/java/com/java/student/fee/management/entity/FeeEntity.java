package com.java.student.fee.management.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FEE_DETAILS")
public class FeeEntity implements Serializable{

	private static final long serialVersionUID = -7892958922725028426L;
	
	@Id
    @Column(name="FEE_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="STUDENT_ID")
	private Long studentId;
	
	@Column(name="STUDENT_NAME")
	private String studentName;
	
	@Column(name="PAYEE")
	private String payee;
	
	@Column(name="CARD_NO")
	private String cardNo;
	
	@Column(name="CARD_TYPE")
	private String cardType;
	
	@Column(name="REFERENCE_NO")
	private String referenceNo;
	
	@Column(name="SUBMIT_TIME")
	private Date timestamp;
	
	@Column(name="SCHOOL_NAME")
	private String schoolName;
	
	@Column(name="CATEGORY")
	private String category;
	
	@Column(name="COUNT")
	private Integer count;
	
	@Column(name="AMOUNT")
	private Double amount;
	
	@Column(name="CURRENCY")
	private String currency;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, cardNo, cardType, category, count, currency, id, payee, referenceNo, schoolName,
				studentId, studentName, timestamp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeeEntity other = (FeeEntity) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(cardNo, other.cardNo)
				&& Objects.equals(cardType, other.cardType) && Objects.equals(category, other.category)
				&& Objects.equals(count, other.count) && Objects.equals(currency, other.currency)
				&& Objects.equals(id, other.id) && Objects.equals(payee, other.payee)
				&& Objects.equals(referenceNo, other.referenceNo) && Objects.equals(schoolName, other.schoolName)
				&& Objects.equals(studentId, other.studentId) && Objects.equals(studentName, other.studentName)
				&& Objects.equals(timestamp, other.timestamp);
	}
	@Override
	public String toString() {
		return "FeeEntity [id=" + id + ", studentId=" + studentId + ", studentName=" + studentName + ", payee=" + payee
				+ ", cardNo=" + cardNo + ", cardType=" + cardType + ", referenceNo=" + referenceNo + ", timestamp="
				+ timestamp + ", schoolName=" + schoolName + ", category=" + category + ", count=" + count + ", amount="
				+ amount + ", currency=" + currency + "]";
	}
	
	

}
