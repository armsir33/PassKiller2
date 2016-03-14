package se.midport.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.midport.entity.AppUser;
import se.midport.entity.Credential;
import se.midport.entity.Role;
import se.midport.form.SearchTermBackingBean;
import se.midport.form.UserForm;
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
	
	@ModelAttribute("userform")
	public UserForm construct() {
		return new UserForm();
	}
	
	@ModelAttribute("credential")
	public Credential getCredential() {
	   return new Credential();
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account( Model model, Principal principal) {
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
		credentialService.save(credential);
		return "redirect:/account.html";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String searchPasswordSubmit(@Valid @ModelAttribute("userform") UserForm userForm) {
		AppUser appUser = new AppUser();
		appUser.setAddress(userForm.getAddress());
		appUser.setCity(userForm.getCity());
		appUser.setCompany(userForm.getCompany());
		appUser.setEmail(userForm.getEmail());
		appUser.setFirstName(userForm.getFirstName());
		appUser.setLastName(userForm.getLastName());
		appUser.setPassword(userForm.getPassword());
		appUser.setPhone(userForm.getPhone());
		appUser.setState(userForm.getState());
		appUser.setTitle(userForm.getTitle());
		appUser.setUsername(userForm.getUsername());
		appUser.setWebsite(userForm.getWebsite());
		appUser.setZip(userForm.getZip());
		appUser.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		appUser.setPassword(encoder.encode(appUser.getPassword()));
		Role userRole = roleRepository.findByName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(userRole);
		appUser.setRoles(roles);
		userService.save(appUser);
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
	public String saveUser(@ModelAttribute("appuser") AppUser appuser) {
		userService.updateByUsername(appuser);
		return "redirect:/account.html";
	}
	
	@RequestMapping(value = "/account/credential-remove/{id}", method = RequestMethod.GET)
	public String removeCredential(@PathVariable Integer id, Model model) {
		credentialService.delete(id);
		return "redirect:/account.html";
	}
}
