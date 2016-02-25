package com.thiraviyum.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.Debit;

public class Dashboard {

	private BigDecimal totalCredit;
	private BigDecimal totalDebit;
	private BigDecimal totalDebitConverted;
	private List<YearlyDashboard> yearlyDashboard;
	private List<Credit> credits;
	private List<Debit> debits;
	
	public BigDecimal getAvgExchangeRate() {
		return totalDebitConverted.divide(totalDebit, 2, RoundingMode.UP);
	}
	
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
}
