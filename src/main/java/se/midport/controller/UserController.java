package se.midport.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.midport.entity.AppUser;
import se.midport.entity.Credential;
import se.midport.entity.Role;
import se.midport.form.SearchTermBackingBean;
import se.midport.repository.RoleRepository;
import se.midport.service.CredentialService;
import se.midport.service.UserService;

@Controller
public class UserController {
	private static final int PAGE_SIZE = 5;

	@Autowired
	private UserService userService;

	@Autowired
	private CredentialService credentialService;

	@Autowired
	private RoleRepository roleRepository;

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

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model, Principal principal) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByModifier(principal.getName(), page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		AppUser appuser = userService.findOneByUsername(principal.getName());
		model.addAttribute("appuser", appuser);
		return "account";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String accountSubmit(@ModelAttribute("credential") Credential credential, Principal principal) {
		credential.setModifier(principal.getName());
		credential.setDate(new Date());
		credentialService.save(credential);
		return "redirect:/account.html";
	}

	@RequestMapping(value = "/account/{pageNo}", method = RequestMethod.GET)
	public String getpass(@PathVariable Integer pageNo, Model model) {
		PageRequest page = new PageRequest(pageNo - 1, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findAll(page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageMax", credentials.getTotalPages());
		return "account";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String searchPasswordSubmit(@Valid @ModelAttribute("appuser") AppUser appUser, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		appUser.setPassword(encoder.encode(appUser.getPassword()));
		Role userRole = roleRepository.findByName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(userRole);
		appUser.setRoles(roles);
		userService.save(appUser);

		// Notify administrator
		userService.emailAdmin(appUser.getUsername());

		return "redirect:/register.html?success=true";
	}

	@RequestMapping(value = "/account/credential-edit/{id}", method = RequestMethod.GET)
	public String editCredential(@PathVariable Integer id, Model model) {
		Credential credential = credentialService.findOne(id);
		model.addAttribute("credential", credential);
		return "credential-edit";
	}

	@RequestMapping(value = "/account/credential-edit/{id}", method = RequestMethod.POST)
	public String saveEditCredential(@ModelAttribute("credential") Credential credential, @PathVariable Integer id) {
		credentialService.updateCredential(id, credential);
		return "redirect:/account.html";
	}

	@RequestMapping(value = "/account/user-edit", method = RequestMethod.GET)
	public String editUser(Principal principal, Model model) {
		AppUser appuser = userService.findByUsername(principal.getName()).get(0);
		model.addAttribute("appuser", appuser);
		return "credential-edit";
	}

	@RequestMapping(value = "/account/user-edit", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("appuser") AppUser appUser) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		appUser.setPassword(encoder.encode(appUser.getPassword()));
		userService.updateByUsername(appUser);
		return "redirect:/account.html";

	}

	@RequestMapping(value = "/account/credential-remove/{id}", method = RequestMethod.GET)
	public String removeCredential(@PathVariable Integer id, Model model) {
		credentialService.delete(id);
		return "redirect:/account.html";
	}

	@RequestMapping(value = "/account/credential/searchAll", method = RequestMethod.GET)
	public String searchAll(Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findAll(page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());
		return "account";
	}
	
	@RequestMapping(value = "/account/credential/searchByCompany/{company}", method = RequestMethod.GET)
	public String searchByCompany(@PathVariable String company, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByCompany(company, page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		return "account";
	}


	@RequestMapping(value = "/account/credential/searchByEnv/{env}", method = RequestMethod.GET)
	public String searchByEnv(@PathVariable String env, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByEnvironment(env, page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		return "account";
	}

	@RequestMapping(value = "/account/credential/searchByDesc/{desc}", method = RequestMethod.GET)
	public String searchByDesc(@PathVariable String desc, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByDesc(desc, page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		return "account";
	}

	@RequestMapping(value = "/account/credential/searchByModifier/{modifier}", method = RequestMethod.GET)
	public String searchByModifier(@PathVariable String modifier, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByModifier(modifier, page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		return "account";
	}

}
