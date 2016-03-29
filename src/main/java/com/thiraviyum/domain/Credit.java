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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thiraviyum.model.YearlyData;
import com.thiraviyum.util.CurrencyFormattedBigDecimalSerializer;

@Entity
@Table(name = "THIRAVIYUM_CREDIT")
public class Credit extends BaseObject implements YearlyData {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	@JsonFormat(pattern = "dd-MMM-yyyy")
	private LocalDate effectiveDate;
	
	@Column(precision = 4, scale = 0, nullable = false)
	@JsonIgnore
	private Integer effectiveYear;
	
	@NotNull
	@Column(precision = 7, scale = 2, nullable = false)
	@JsonSerialize(using = CurrencyFormattedBigDecimalSerializer.class)
	private BigDecimal amount;
	
	@NotBlank
	@Length(min = 1, max = 15)
	@Column(length = 15, nullable = false)
	private String source;
	
	@Length(max = 100)
	@Column(length = 100)
	@JsonIgnore
	private String comment;
	
	@OneToOne
	@JoinColumn(name = "CREDIT_USER")
	@JsonIgnore
	private User creditUser;
	
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public User getCreditUser() {
		return creditUser;
	}
	public void setCreditUser(User creditUser) {
		this.creditUser = creditUser;
	}
}
