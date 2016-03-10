package com.thiraviyum.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thiraviyum.domain.User;
import com.thiraviyum.service.UserService;

@Controller
public class SessionController {
	private static final Logger logger = LoggerFactory.getLogger(SessionController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && !AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
			return "redirect:/";
		}
		logger.info("Requesting login");
		return "login";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(@Valid @ModelAttribute("user") User user, BindingResult result, 
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("errorMsg", "Unable to sign up. Please try again later.");
			return "redirect:/login";
		}
		
		userService.register(user);
		redirectAttributes.addFlashAttribute("successMsg", "You have been registered successfully. Please sign in with the Email and Password.");
		return "redirect:/login";
	}
}
