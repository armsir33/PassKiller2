package se.midport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.midport.entity.AppUser;
import se.midport.entity.Credential;
import se.midport.form.SearchTermBackingBean;
import se.midport.service.CredentialService;
import se.midport.service.UserService;

@Controller
public class UserController {
	private static final int PAGE_SIZE = 5;
	
	@Autowired
	private CredentialService credentialService;
	
	@Autowired
	private UserService userService;

	@ModelAttribute("searchTermBackingBean")
	public SearchTermBackingBean getSearchTermBackingBean() {
		return new SearchTermBackingBean();
	}
	
	@ModelAttribute("appuser")
	public AppUser construct() {
		return new AppUser();
	}
	
	@ModelAttribute("credential")
	public Credential getCredential() {
	   return new Credential();
	}

	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String searchPasswordSubmit(@ModelAttribute("appuser") AppUser appUser, Model model) {
		userService.save(appUser);
		return "redirect:/register.html?success=true";
	}

	@RequestMapping(value = "/admin/users/search", method = RequestMethod.GET)
	public String searchPasswordForm(Model model) {
		return "admin";
	}

	@RequestMapping(value = "/admin/users/search", method = RequestMethod.POST)
	public String searchPasswordSubmit(
			@ModelAttribute("searchTermBackingBean") SearchTermBackingBean searchTermBackingBean, Model model) {
		String searchTerm = searchTermBackingBean.getSearchTerm();
		List<AppUser> users = userService.findByUsername(searchTerm);
		model.addAttribute("users", users);
		model.addAttribute("pageNo", 0);
		model.addAttribute("pageMax", -1);
		model.addAttribute("user-tab-status", "active");
		
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findAll(page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());
		model.addAttribute("credential-tab-status", "");
		
		return "admin";
	}
	
	@RequestMapping(value = "/admin/user-remove/{id}", method = RequestMethod.GET)
	public String removeCredential(@PathVariable Integer id, Model model) {
		userService.delete(id);
		return "redirect:/admin.html";
	}
}
