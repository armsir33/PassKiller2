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
	public String account(Model model, Principal principal) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByModifier(principal.getName(), page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		AppUser appuser = userService.findOneByUsername(principal.getName());
		UserForm userForm = new UserForm();
		userForm.setAddress(appuser.getAddress());
		userForm.setCity(appuser.getCity());
		userForm.setCompany(appuser.getCompany());
		userForm.setEmail(appuser.getEmail());
		userForm.setFirstName(appuser.getFirstName());
		userForm.setLastName(appuser.getLastName());
		userForm.setPhone(appuser.getPhone());
		userForm.setState(appuser.getState());
		userForm.setTitle(appuser.getTitle());
		userForm.setUsername(appuser.getUsername());
		userForm.setWebsite(appuser.getWebsite());
		userForm.setZip(appuser.getZip());
		userForm.setPassword(appuser.getPassword());
		model.addAttribute("userform", userForm);
		return "account";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String accountSubmit(@ModelAttribute("credential") Credential credential, Principal principal) {
		credential.setModifier(principal.getName());
		credential.setDate(new Date());
		credentialService.save(credential);
		return "redirect:/account.html";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String searchPasswordSubmit(@Valid @ModelAttribute("userform") UserForm userForm) {
		// Check if username exist or not
		boolean isExistUsername = userService.checkIfUsernameExist(userForm.getUsername());
		if (isExistUsername)
			return "redirect:/register.html?failed=true";
		else {
			AppUser appUser = new AppUser();
			appUser.setAddress(userForm.getAddress());
			appUser.setCity(userForm.getCity());
			appUser.setCompany(userForm.getCompany());
			String email = userForm.getEmail();
			appUser.setEmail(email);
			String firstName = userForm.getFirstName();
			appUser.setFirstName(firstName);
			String lastName = userForm.getLastName();
			appUser.setLastName(lastName);
			String password = userForm.getPassword();
			appUser.setPhone(userForm.getPhone());
			appUser.setState(userForm.getState());
			appUser.setTitle(userForm.getTitle());
			String username = userForm.getUsername();
			appUser.setUsername(username);
			appUser.setWebsite(userForm.getWebsite());
			appUser.setZip(userForm.getZip());
			appUser.setEnabled(false);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			appUser.setPassword(encoder.encode(password));
			Role userRole = roleRepository.findByName("ROLE_USER");
			List<Role> roles = new ArrayList<Role>();
			roles.add(userRole);
			appUser.setRoles(roles);
			userService.save(appUser);
			
			// Notify administrator
			userService.emailAdmin(username);
			
			return "redirect:/register.html?success=true";
		}
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
		UserForm userForm = new UserForm();
		userForm.setAddress(appuser.getAddress());
		userForm.setCity(appuser.getCity());
		userForm.setCompany(appuser.getCompany());
		userForm.setEmail(appuser.getEmail());
		userForm.setFirstName(appuser.getFirstName());
		userForm.setLastName(appuser.getLastName());
		userForm.setPassword(appuser.getPassword());
		userForm.setPhone(appuser.getPhone());
		userForm.setState(appuser.getState());
		userForm.setTitle(appuser.getTitle());
		userForm.setUsername(appuser.getUsername());
		userForm.setWebsite(appuser.getWebsite());
		userForm.setZip(appuser.getZip());
		model.addAttribute("userform", userForm);
		return "credential-edit";
	}

	@RequestMapping(value = "/account/user-edit", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userform") UserForm userForm) {
		String password = userForm.getPassword();
		String repassword = userForm.getRepassword();
		
		if (!password.equals(repassword))
			return "redirect:/account.html?password_mismatch_failed=true";

		AppUser appUser = new AppUser();
		appUser.setAddress(userForm.getAddress());
		appUser.setCity(userForm.getCity());
		appUser.setCompany(userForm.getCompany());
		appUser.setEmail(userForm.getEmail());
		appUser.setFirstName(userForm.getFirstName());
		appUser.setLastName(userForm.getLastName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		appUser.setPassword(encoder.encode(userForm.getPassword()));
		appUser.setPhone(userForm.getPhone());
		appUser.setState(userForm.getState());
		appUser.setTitle(userForm.getTitle());
		appUser.setUsername(userForm.getUsername());
		appUser.setWebsite(userForm.getWebsite());
		appUser.setZip(userForm.getZip());
		userService.updateByUsername(appUser);
		return "redirect:/account.html";

	}

	@RequestMapping(value = "/account/credential-remove/{id}", method = RequestMethod.GET)
	public String removeCredential(@PathVariable Integer id, Model model) {
		credentialService.delete(id);
		return "redirect:/account.html";
	}
	
}
