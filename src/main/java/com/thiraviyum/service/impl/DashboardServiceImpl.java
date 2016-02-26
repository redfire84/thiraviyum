package com.thiraviyum.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiraviyum.dao.CreditDao;
import com.thiraviyum.dao.DebitDao;
import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.Debit;
import com.thiraviyum.domain.User;
import com.thiraviyum.model.Dashboard;
import com.thiraviyum.model.YearlyDashboard;
import com.thiraviyum.model.YearlyData;
import com.thiraviyum.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {
	private final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	
	@Autowired
	private CreditDao creditDao;
	@Autowired
	private DebitDao debitDao;

	@Override
	@Transactional
	public Dashboard getDashboard(User user) {
		Dashboard dashboard = new Dashboard();
		List<Credit> credits = creditDao.findAllByCreditUserOrderByEffectiveDateDesc(user);
		List<Debit> debits = debitDao.findAllByDebitUserOrderByEffectiveDateDesc(user);
		
		// Credit
		BigDecimal totalCredit = BigDecimal.ZERO;
		for (Credit credit : credits) {
			totalCredit = totalCredit.add(credit.getAmount());
		}
		
		// Debit
		BigDecimal totalDebit = BigDecimal.ZERO;
		BigDecimal totalDebitConverted = BigDecimal.ZERO;
		for (Debit debit : debits) {
			totalDebit = totalDebit.add(debit.getAmount());
			totalDebitConverted = totalDebitConverted.add(debit.getAmount().multiply(debit.getExchangeRate()));
		}
		
		dashboard.setTotalCredit(totalCredit);
		dashboard.setTotalDebit(totalDebit);
		dashboard.setTotalDebitConverted(totalDebitConverted);
		dashboard.setCredits(credits);
		dashboard.setDebits(debits);
		if (totalDebit.compareTo(BigDecimal.ZERO) > 0 && totalDebitConverted.compareTo(BigDecimal.ZERO) > 0) {
			dashboard.setAvgExchangeRate(totalDebitConverted.divide(totalDebit, 2, RoundingMode.UP));
		}
		if (totalDebit.compareTo(BigDecimal.ZERO) > 0 && totalCredit.compareTo(BigDecimal.ZERO) > 0) {
			dashboard.setScore(totalDebit.divide(totalCredit, 3, RoundingMode.UP).multiply(ONE_HUNDRED));
		}
		populateYearlyData(dashboard);
		return dashboard;
	}

	private void populateYearlyData(Dashboard dashboard) {
		Map<String, List<Credit>> yearlyCredits = splitYearlyData(dashboard.getCredits());
		Map<String, List<Debit>> yearlyDebits = splitYearlyData(dashboard.getDebits());
		
		// all available years
		Set<String> years = new TreeSet<String>();
		years.addAll(yearlyCredits.keySet());
		years.addAll(yearlyDebits.keySet());
		
		List<YearlyDashboard> yearlyDashboard = new ArrayList<YearlyDashboard>();
		for (String year : years) {
			YearlyDashboard yDashboard = new YearlyDashboard();
			int months = effectiveMonths(year);
			// Yearly data
			BigDecimal oneYearCredit = BigDecimal.ZERO;
			if (yearlyCredits.get(year) != null) {
				for (Credit credit : yearlyCredits.get(year)) {
					oneYearCredit = oneYearCredit.add(credit.getAmount());
				}
			}
			else {
				yearlyCredits.put(year, Collections.emptyList());
			}
			
			BigDecimal oneYearDebit = BigDecimal.ZERO;
			BigDecimal oneYearDebitConverted = BigDecimal.ZERO;
			if (yearlyDebits.get(year) != null) {
				for (Debit debit : yearlyDebits.get(year)) {
					oneYearDebit = oneYearDebit.add(debit.getAmount());
					oneYearDebitConverted = oneYearDebitConverted.add(debit.getAmount().multiply(debit.getExchangeRate()));
				}
			}
			else {
				yearlyDebits.put(year, Collections.emptyList());
			}
			
			// Monthly data
			BigDecimal oneMonthCredit = BigDecimal.ZERO;
			BigDecimal oneMonthDebit = BigDecimal.ZERO;
			BigDecimal oneMonthDebitConverted = BigDecimal.ZERO;
			if (months > 0) {
				BigDecimal bMonths = new BigDecimal(months);
				if (BigDecimal.ZERO.compareTo(oneYearCredit) != 0) {
					oneMonthCredit = oneYearCredit.divide(bMonths, RoundingMode.UP);
				}
				if (BigDecimal.ZERO.compareTo(oneYearDebit) != 0) {
					oneMonthDebit = oneYearDebit.divide(bMonths, RoundingMode.UP);
				}
				if (BigDecimal.ZERO.compareTo(oneYearDebitConverted) != 0) {
					oneMonthDebitConverted = oneYearDebitConverted.divide(bMonths, RoundingMode.UP);
				}
			}
			
			yDashboard.setYear(year);
			yDashboard.setOneYearCredit(oneYearCredit);
			yDashboard.setOneMonthCredit(oneMonthCredit);
			yDashboard.setOneYearDebit(oneYearDebit);
			yDashboard.setOneMonthDebit(oneMonthDebit);
			yDashboard.setOneYearDebitConverted(oneYearDebitConverted);
			yDashboard.setOneMonthDebitConverted(oneMonthDebitConverted);
			if (oneYearDebit.compareTo(BigDecimal.ZERO) > 0 && oneYearDebitConverted.compareTo(BigDecimal.ZERO) > 0) {
				yDashboard.setAvgExchangeRate(oneYearDebitConverted.divide(oneYearDebit, 2, RoundingMode.UP));
			}
			yDashboard.setCredits(yearlyCredits.get(year));
			yDashboard.setDebits(yearlyDebits.get(year));
			yearlyDashboard.add(yDashboard);
		}
		
		dashboard.setYearlyDashboard(yearlyDashboard);
	}
	
	private <T extends YearlyData> Map<String, List<T>> splitYearlyData(List<T> list) {
		Map<String, List<T>> yearlyData = new HashMap<String, List<T>>();
		
		List<T> ylist = new ArrayList<T>();
		String previousYear = null;
		for (T data : list) {
			if(previousYear != null && !previousYear.equals(data.getEffectiveYear())) {
				yearlyData.put(previousYear, ylist);
				ylist = new ArrayList<T>();
				
			}
			previousYear = data.getEffectiveYear();
			ylist.add(data);
		}
		if (ylist.size() > 0) {
			yearlyData.put(previousYear, ylist);
		}
		return yearlyData;
	}
	
	private int effectiveMonths(String year) {
		int paramYear = Integer.valueOf(year);
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		if (currentYear == paramYear) {
			return Calendar.getInstance().get(Calendar.MONTH) + 1;
		}
		else if (currentYear > paramYear) {
			return 12;
		}
		else {
			return 0;
		}
	}
}
