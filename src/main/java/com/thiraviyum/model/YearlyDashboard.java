package com.thiraviyum.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.Debit;

public class YearlyDashboard {

	private String year;
	private BigDecimal oneYearCredit;
	private BigDecimal oneMonthCredit;
	private BigDecimal oneYearDebit;
	private BigDecimal oneMonthDebit;
	private BigDecimal oneYearDebitConverted;
	private BigDecimal oneMonthDebitConverted;
	private List<Credit> credits;
	private List<Debit> debits;
	
	public BigDecimal getAvgExchangeRate() {
		return oneYearDebitConverted.divide(oneYearDebit, 2, RoundingMode.UP);
	}
	
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
}
