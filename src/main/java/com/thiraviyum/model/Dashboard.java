package com.thiraviyum.model;

import java.math.BigDecimal;
import java.util.List;

import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.Debit;

public class Dashboard {

	private BigDecimal totalCredit = BigDecimal.ZERO;
	private BigDecimal totalDebit = BigDecimal.ZERO;
	private BigDecimal totalDebitConverted = BigDecimal.ZERO;
	private BigDecimal avgExchangeRate = BigDecimal.ZERO;
	private BigDecimal score = BigDecimal.ZERO;
	private List<YearlyDashboard> yearlyDashboard;
	private List<Credit> credits;
	private List<Debit> debits;
	
	public BigDecimal getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(BigDecimal totalCredit) {
		this.totalCredit = totalCredit;
	}
	public BigDecimal getTotalDebit() {
		return totalDebit;
	}
	public void setTotalDebit(BigDecimal totalDebit) {
		this.totalDebit = totalDebit;
	}
	public BigDecimal getTotalDebitConverted() {
		return totalDebitConverted;
	}
	public void setTotalDebitConverted(BigDecimal totalDebitConverted) {
		this.totalDebitConverted = totalDebitConverted;
	}
	public List<YearlyDashboard> getYearlyDashboard() {
		return yearlyDashboard;
	}
	public void setYearlyDashboard(List<YearlyDashboard> yearlyDashboard) {
		this.yearlyDashboard = yearlyDashboard;
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
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
}
