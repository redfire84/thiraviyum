package com.thiraviyum.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.thiraviyum.domain.Debit;
import com.thiraviyum.domain.User;
import com.thiraviyum.model.Dashboard;
import com.thiraviyum.model.YearlyDashboard;
import com.thiraviyum.model.chart.ChartData;
import com.thiraviyum.model.chart.ColData;
import com.thiraviyum.model.chart.Column;
import com.thiraviyum.model.chart.DateColumn;
import com.thiraviyum.model.chart.RowData;
import com.thiraviyum.service.DashboardService;

@Controller
@RequestMapping(value = "/dashboard")
@SessionAttributes(names = "dashboard")
public class DashboardController {
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@Autowired
	private DashboardService dashboardService;
	
	@ModelAttribute("dashboard")
	public Dashboard populateDashboard(@AuthenticationPrincipal User user) {
		return dashboardService.getDashboard(user);
	}
	
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String home(@AuthenticationPrincipal User user) {
		return "dashboard/show";
	}
	
	@RequestMapping(value = "{year}/list", method = RequestMethod.GET)
	public String detail(@PathVariable("year") String year, Model model) {
		model.addAttribute("year", year);
		return "dashboard/list";
	}
	
	@RequestMapping(value = "/chart/xe-rate", method = RequestMethod.GET)
	@ResponseBody
	public ChartData xeRateChart(@ModelAttribute("dashboard") Dashboard dashboard) {
		ChartData data = new ChartData();
		data.addCol(new ColData(null, null, null, "date"));
		data.addCol(new ColData(null, "Average", null, "number"));
		data.addCol(new ColData(null, "XE Rate", null, "number"));
		
		if (dashboard.getYearlyDashboard() != null) {
			for (YearlyDashboard yearlyDashboard : dashboard.getYearlyDashboard()) {
				if (yearlyDashboard.getDebits() != null) {
					for (Debit debit : yearlyDashboard.getDebits()) {
						RowData row = new RowData();
						row.addColumn(new DateColumn(debit.getEffectiveDate(), null));
						row.addColumn(new Column<BigDecimal>(dashboard.getAvgExchangeRate(), null));
						row.addColumn(new Column<BigDecimal>(debit.getExchangeRate(), null));
						data.addRow(row);
					}
				}
			}
		}
		return data;
	}
	
	@RequestMapping(value = "/chart/yearly-detail", method = RequestMethod.GET)
	@ResponseBody
	public ChartData yearlyDetailChart(@ModelAttribute("dashboard") Dashboard dashboard) {
		ChartData data = new ChartData();
		data.addCol(new ColData(null, null, null, "date"));
		data.addCol(new ColData(null, "Credit USD", null, "number"));
		data.addCol(new ColData(null, "Debit USD", null, "number"));
		data.addCol(new ColData(null, "Debit INR", null, "number"));
		
		if (dashboard.getYearlyDashboard() != null) {
			for (YearlyDashboard yearlyDashboard : dashboard.getYearlyDashboard()) {
				RowData row = new RowData();
				row.addColumn(new Column<String>("Date(" + yearlyDashboard.getYear() + ", 0)", yearlyDashboard.getYear()));
				row.addColumn(new Column<BigDecimal>(yearlyDashboard.getOneYearCredit(), null));
				row.addColumn(new Column<BigDecimal>(yearlyDashboard.getOneYearDebit(), null));
				row.addColumn(new Column<BigDecimal>(yearlyDashboard.getOneYearDebitConverted(), null));
				data.addRow(row);
			}
		}
		return data;
	}
}
