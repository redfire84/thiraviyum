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
@Table(name = "THIRAVIYUM_CREDIT")
public class Credit extends BaseObject implements YearlyData {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "MMM-dd")
	private Date effectiveDate;
	@Column
	private String effectiveYear;
	@Column
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal amount;
	@Column
	private String source;
	@Column
	private String comment;
	@OneToOne
	@JoinColumn(name = "CREDIT_USER")
	private User creditUser;
	
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
