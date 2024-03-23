package com.java.student.fee.management.model;

import java.io.Serializable;
import java.util.Objects;

public class FeesType implements Serializable {

	private static final long serialVersionUID = 1007239213935397244L;
	
	private String category;
	private Integer count;
	private Double amount;
	private String currency;
	
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
		return Objects.hash(amount, category, count, currency);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeesType other = (FeesType) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(category, other.category)
				&& Objects.equals(count, other.count) && Objects.equals(currency, other.currency);
	}
	@Override
	public String toString() {
		return "FeesType [category=" + category + ", count=" + count + ", amount=" + amount + ", currency=" + currency
				+ "]";
	}
	
}
