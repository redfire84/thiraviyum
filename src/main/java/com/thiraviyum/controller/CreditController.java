package com.thiraviyum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.User;
import com.thiraviyum.model.DataTable;
import com.thiraviyum.service.CreditService;

@Controller
@RequestMapping("/credit")
@SessionAttributes(names = "dashboard")
public class CreditController {
	@Autowired
	private CreditService creditService;
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("credit", new Credit());
		return "credit/new";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("credit") Credit credit, BindingResult result, 
			@AuthenticationPrincipal User user, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
		if (result.hasErrors()) {
			return "credit/new";
		}
		
		creditService.save(credit, user);
		redirectAttributes.addFlashAttribute("successMsg", "Credit data has been added successfully.");
		sessionStatus.setComplete();
		return "redirect:/dashboard/show";
	}
	
	@RequestMapping(value = "{year}/list", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public DataTable<Credit> list(@PathVariable String year, @AuthenticationPrincipal User user) {
		DataTable<Credit> dataTable = new DataTable<Credit>();
		dataTable.setData(creditService.find(year, user));
		return dataTable;
	}
	
	@RequestMapping(value = "{id}/show")
	public String show(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		Credit credit = creditService.find(id);
		if (credit == null) {
			redirectAttributes.addFlashAttribute("errorMsg", "Unable to find the credit data.");
			return "redirect:/dashboard/show";
		}
		model.addAttribute("credit", credit);
		model.addAttribute("errorMsg", "Are you sure you want to delete this credit?");
		return "credit/show";
	}
	
	@RequestMapping(value= "{id}/delete", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
		Credit credit = creditService.find(id);
		if (credit == null) {
			redirectAttributes.addFlashAttribute("errorMsg", "Unable to find the credit data to delete.");
			return "redirect:/dashboard/show";
		}
		creditService.delete(credit);
		sessionStatus.setComplete();
		redirectAttributes.addFlashAttribute("successMsg", "Credit data has been deleted successfully.");
		return "redirect:/dashboard/" + credit.getEffectiveYear() + "/list";
	}
}
