package com.thiraviyum.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thiraviyum.model.YearlyData;
import com.thiraviyum.util.CurrencyFormattedBigDecimalSerializer;

@Entity
@Table(name = "THIRAVIYUM_DEBIT")
public class Debit extends BaseObject implements YearlyData {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	@JsonFormat(pattern = "dd-MMM-yyyy")
	private LocalDate effectiveDate;
	
	@Column(precision = 4, scale = 0, nullable = false)
	@JsonIgnore
	private Integer effectiveYear;
	
	@Column(precision = 7, scale = 2, nullable = false)
	@JsonSerialize(using = CurrencyFormattedBigDecimalSerializer.class)
	private BigDecimal amount;
	
	@Column(precision = 5, scale = 2, nullable = false)
	@JsonSerialize(using = CurrencyFormattedBigDecimalSerializer.class)
	private BigDecimal exchangeRate;
	
	@Column(length = 100)
	@JsonIgnore
	private String comment;
	
	@OneToOne
	@JoinColumn(name = "DEBIT_USER")
	@JsonIgnore
	private User debitUser;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	@Override
	public Integer getEffectiveYear() {
		return effectiveYear;
	}
	public void setEffectiveYear(Integer effectiveYear) {
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
