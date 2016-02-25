package com.thiraviyum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.RequireJS;

@Controller
@RequestMapping(value = "/webjarsjs")
public class WebJarsController {

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = "application/javascript")
	public String home() {
		return RequireJS.getSetupJavaScript("/webjars/");
	}
}
