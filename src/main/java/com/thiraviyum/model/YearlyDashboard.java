package com.thiraviyum.model;

import java.math.BigDecimal;
import java.util.List;

import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.Debit;

public class YearlyDashboard {

	private String year;
	private BigDecimal oneYearCredit = BigDecimal.ZERO;
	private BigDecimal oneMonthCredit = BigDecimal.ZERO;
	private BigDecimal oneYearDebit = BigDecimal.ZERO;
	private BigDecimal oneMonthDebit = BigDecimal.ZERO;
	private BigDecimal oneYearDebitConverted = BigDecimal.ZERO;
	private BigDecimal oneMonthDebitConverted = BigDecimal.ZERO;
	private BigDecimal avgExchangeRate = BigDecimal.ZERO;
	private List<Credit> credits;
	private List<Debit> debits;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public BigDecimal getOneYearCredit() {
		return oneYearCredit;
	}
	public void setOneYearCredit(BigDecimal oneYearCredit) {
		this.oneYearCredit = oneYearCredit;
	}
	public BigDecimal getOneMonthCredit() {
		return oneMonthCredit;
	}
	public void setOneMonthCredit(BigDecimal oneMonthCredit) {
		this.oneMonthCredit = oneMonthCredit;
	}
	public BigDecimal getOneYearDebit() {
		return oneYearDebit;
	}
	public void setOneYearDebit(BigDecimal oneYearDebit) {
		this.oneYearDebit = oneYearDebit;
	}
	public BigDecimal getOneMonthDebit() {
		return oneMonthDebit;
	}
	public void setOneMonthDebit(BigDecimal oneMonthDebit) {
		this.oneMonthDebit = oneMonthDebit;
	}
	public BigDecimal getOneYearDebitConverted() {
		return oneYearDebitConverted;
	}
	public void setOneYearDebitConverted(BigDecimal oneYearDebitConverted) {
		this.oneYearDebitConverted = oneYearDebitConverted;
	}
	public BigDecimal getOneMonthDebitConverted() {
		return oneMonthDebitConverted;
	}
	public void setOneMonthDebitConverted(BigDecimal oneMonthDebitConverted) {
		this.oneMonthDebitConverted = oneMonthDebitConverted;
	}
	public List<Credit> getCredits() {
		return credits;
	}
	public void setCredits(List<Credit> credits) {
		this.credits = credits;
	}
	public List<Debit> getDebits() {
		return debits;
	}
	public void setDebits(List<Debit> debits) {
		this.debits = debits;
	}
	public BigDecimal getAvgExchangeRate() {
		return avgExchangeRate;
	}
	public void setAvgExchangeRate(BigDecimal avgExchangeRate) {
		this.avgExchangeRate = avgExchangeRate;
	}
}
