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

import com.thiraviyum.domain.Debit;
import com.thiraviyum.domain.User;
import com.thiraviyum.model.DataTable;
import com.thiraviyum.service.DebitService;

@Controller
@RequestMapping("/debit")
@SessionAttributes(names = "dashboard")
public class DebitController {
	@Autowired
	private DebitService debitService;
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("debit", new Debit());
		return "debit/new";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("debit") Debit debit, BindingResult result, 
			@AuthenticationPrincipal User user,	RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
		if (result.hasErrors()) {
			return "debit/new";
		}
		
		debitService.save(debit, user);
		sessionStatus.setComplete();
		redirectAttributes.addFlashAttribute("successMsg", "Debit data has been added successfully.");
		return "redirect:/dashboard/show";
	}
	
	@RequestMapping(value = "{year}/list", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public DataTable<Debit> list(@PathVariable Integer year, @AuthenticationPrincipal User user) {
		DataTable<Debit> dataTable = new DataTable<Debit>();
		dataTable.setData(debitService.find(year, user));
		return dataTable;
	}
	
	@RequestMapping(value = "{id}/show")
	public String show(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes, 
			@AuthenticationPrincipal User user) {
		Debit debit = debitService.find(id, user);
		if (debit == null) {
			redirectAttributes.addFlashAttribute("errorMsg", "Unable to find the debit data.");
			return "redirect:/dashboard/show";
		}
		model.addAttribute("debit", debit);
		model.addAttribute("errorMsg", "Are you sure you want to delete this debit?");
		return "debit/show";
	}
	
	@RequestMapping(value= "{id}/delete", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, 
			SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		Debit debit = debitService.find(id, user);
		if (debit == null) {
			redirectAttributes.addFlashAttribute("errorMsg", "Unable to find the debit data to delete.");
			return "redirect:/dashboard/show";
		}
		debitService.delete(debit);
		sessionStatus.setComplete();
		redirectAttributes.addFlashAttribute("successMsg", "Debit data has been deleted successfully.");
		return "redirect:/dashboard/" + debit.getEffectiveYear() + "/list";
	}
}
