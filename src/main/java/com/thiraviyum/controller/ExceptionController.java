package com.thiraviyum.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("errorController")
public class ExceptionController implements ErrorController {
	@Autowired
	private ErrorAttributes errorAttributes;
	
	@RequestMapping(value = "/error")
	public ModelAndView error(HttpServletRequest request) {
		return new ModelAndView("error", errorAttributes.getErrorAttributes(new ServletRequestAttributes(request), false));
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
