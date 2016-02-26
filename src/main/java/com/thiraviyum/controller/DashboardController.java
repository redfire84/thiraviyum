package com.thiraviyum.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.thiraviyum.domain.User;
import com.thiraviyum.model.Dashboard;
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
	
	@RequestMapping(value = "{year}/show", method = RequestMethod.GET)
	public String detail(@PathVariable("year") String year, Model model) {
		model.addAttribute("year", year);
		return "dashboard/list";
	}
}
