package com.thiraviyum.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thiraviyum.model.YearlyData;
import com.thiraviyum.util.BigDecimalSerializer;

@Entity
@Table(name = "THIRAVIYUM_DEBIT")
public class Debit extends BaseObject implements YearlyData {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "MMM dd, yyyy")
	private Date effectiveDate;
	@Column
	private String effectiveYear;
	@Column
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal amount;
	@Column
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal exchangeRate;
	@Column
	private String comment;
	@OneToOne
	@JoinColumn(name = "DEBIT_USER")
	private User debitUser;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	@Override
	public String getEffectiveYear() {
		return effectiveYear;
	}
	public void setEffectiveYear(String effectiveYear) {
		this.effectiveYear = effectiveYear;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public User getDebitUser() {
		return debitUser;
	}
	public void setDebitUser(User debitUser) {
		this.debitUser = debitUser;
	}
}
