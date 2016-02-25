package com.thiraviyum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class SessionController {
	private static final Logger logger = LoggerFactory.getLogger(SessionController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && !AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
			return "redirect:/";
		}
		logger.info("Requesting login");
		return "login";
	}
}
