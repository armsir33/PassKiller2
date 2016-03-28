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
import se.midport.entity.Contact;
import se.midport.entity.Credential;
import se.midport.form.SearchTermBackingBean;
import se.midport.service.ContactService;
import se.midport.service.CredentialService;
import se.midport.service.UserService;

@Controller
public class AdminController {
	
	private static final int PAGE_SIZE = 5;
	
	@Autowired
	private CredentialService credentialService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContactService contactService;
	
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
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value = "/admin/browse-credentials", method = RequestMethod.GET)
	public String browseCredentials(Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByRange("Public", page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());
		
		return "admin-browse-credentials";
	}
	
	@RequestMapping(value = "/admin/browse-users", method = RequestMethod.GET)
	public String browseUsers(Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<AppUser> users = userService.findAll(page);
		model.addAttribute("users", users.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", users.getTotalPages());
		return "admin-browse-users";
	}
	
	@RequestMapping(value = "/admin/browse-contacts", method = RequestMethod.GET)
	public String browseContacts(Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Contact> contacts = contactService.findAll(page);
		model.addAttribute("contacts", contacts.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", contacts.getTotalPages());
		return "admin-browse-contacts";
	}

	@RequestMapping(value = "/admin/browse-credential/{pageNo}", method = RequestMethod.GET)
	public String getpass(@PathVariable Integer pageNo, Model model) {
		PageRequest page = new PageRequest(pageNo - 1, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findAll(page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageMax", credentials.getTotalPages());
		return "admin-browse-credentials";
	}
	
	@RequestMapping(value = "/admin/browse-contacts/{pageNo}", method = RequestMethod.GET)
	public String browseContacts(@PathVariable Integer pageNo, Model model) {
		PageRequest page = new PageRequest(pageNo - 1, PAGE_SIZE);
		Page<Contact> contacts = contactService.findAll(page);
		model.addAttribute("contacts", contacts.getContent());
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageMax", contacts.getTotalPages());
		return "admin-browse-contacts";
	}
	
	@RequestMapping(value = "/admin/browse-users/{pageNo}", method = RequestMethod.GET)
	public String browerUsers(@PathVariable Integer pageNo, Model model) {
		PageRequest page = new PageRequest(pageNo - 1, PAGE_SIZE);
		Page<AppUser> users = userService.findAll(page);
		model.addAttribute("users", users.getContent());
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageMax", users.getTotalPages());
		return "admin-browse-users";
	}

	@RequestMapping(value="/admin/credential/search", method=RequestMethod.GET)
    public String searchPasswordForm(Model model) {
        return "admin-browse-credentials";
    }
	
	@RequestMapping(value = "/admin/credential/search", method = RequestMethod.POST)
	public String searchPasswordSubmit(@ModelAttribute("searchTermBackingBean") SearchTermBackingBean searchTermBackingBean, Model model) {
		String searchTerm = searchTermBackingBean.getSearchTerm();
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		if(searchTerm == null || searchTerm.isEmpty()) {
			Page<Credential> credentials = credentialService.findAll(page);
			model.addAttribute("credentials", credentials.getContent());
			model.addAttribute("pageNo", 1);
			model.addAttribute("pageMax", credentials.getTotalPages());
		} else {
			List<Credential> credentials = credentialService.findByName(searchTerm);
			model.addAttribute("credentials", credentials);
		}
		return "admin-browse-credentials";
	}
	
	@RequestMapping(value = "/admin/credential-remove/{id}", method = RequestMethod.GET)
	public String removeCredential(@PathVariable Integer id, Model model) {
		credentialService.delete(id);
		return "redirect:/admin/browse-credentials.html";
	}
	
	@RequestMapping(value = "/admin/contact-remove/{id}", method = RequestMethod.GET)
	public String removeContact(@PathVariable Integer id, Model model) {
		contactService.delete(id);
		return "redirect:/admin/browse-contacts.html";
	}
	
	@RequestMapping(value = "/admin/user-remove/{id}", method = RequestMethod.GET)
	public String removeUser(@PathVariable Integer id, Model model) {
		userService.delete(id);
		return "redirect:/admin/browse-users.html";
	}
	
	@RequestMapping(value = "/admin/user-activate/{id}", method = RequestMethod.GET)
	public String activateUser(@PathVariable Integer id, Model model) {
		userService.updateActivateStatus(id);
		AppUser appUser = userService.findOne(id);
		
		// Notify user
		String username = appUser.getUsername();
		String email = appUser.getEmail();
		userService.email(username, email);
		
		return "redirect:/admin/browse-users.html";
	}
	
	@RequestMapping(value = "/admin/user-deactivate/{id}", method = RequestMethod.GET)
	public String deactivateUser(@PathVariable Integer id, Model model) {
		userService.updateDeactivateStatus(id);
		return "redirect:/admin/browse-users.html";
	}
	
	@RequestMapping(value = "/admin/users/search", method = RequestMethod.GET)
	public String searchUsersForm(Model model) {
		return "admin-browse-users";
	}
	
	@RequestMapping(value = "/admin/users/search", method = RequestMethod.POST)
	public String searchUsersSubmit(
			@ModelAttribute("searchTermBackingBean") SearchTermBackingBean searchTermBackingBean, Model model) {
		String searchTerm = searchTermBackingBean.getSearchTerm();
		if(searchTerm == null || searchTerm.isEmpty()) {
			PageRequest page = new PageRequest(0, PAGE_SIZE);
			Page<AppUser> users = userService.findAll(page);
			model.addAttribute("users", users.getContent());
			model.addAttribute("pageNo", 1);
			model.addAttribute("pageMax", users.getTotalPages());
		} else {
			List<AppUser> users = userService.findByUsername(searchTerm);
			model.addAttribute("users", users);
			model.addAttribute("pageNo", 0);
			model.addAttribute("pageMax", -1);
		}

		return "admin-browse-users";
	}
	
	@RequestMapping(value = "/admin/users/searchAll", method = RequestMethod.GET)
	public String searchAllUsers(Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<AppUser> users = userService.findAll(page);
		model.addAttribute("users", users.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", users.getTotalPages());

		return "admin-browse-users";
	}
	
	@RequestMapping(value = "/admin/users/searchByUsername/{username}", method = RequestMethod.GET)
	public String searchByUsername(@PathVariable String username, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<AppUser> users = userService.findByUsername(username, page);
		model.addAttribute("users", users.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", users.getTotalPages());

		return "admin-browse-users";
	}
	
	@RequestMapping(value = "/admin/users/searchByFirstName/{firstName}", method = RequestMethod.GET)
	public String searchByFirstName(@PathVariable String firstName, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<AppUser> users = userService.findByFirstName(firstName, page);
		model.addAttribute("users", users.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", users.getTotalPages());

		return "admin-browse-users";
	}
	
	@RequestMapping(value = "/admin/users/searchByLastName/{lastName}", method = RequestMethod.GET)
	public String searchByLastName(@PathVariable String lastName, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<AppUser> users = userService.findByLastName(lastName, page);
		model.addAttribute("users", users.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", users.getTotalPages());

		return "admin-browse-users";
	}
	
	@RequestMapping(value = "/admin/users/searchByEmail/{email}", method = RequestMethod.GET)
	public String searchUsersByEmail(@PathVariable String email, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<AppUser> users = userService.findByEmail(email, page);
		model.addAttribute("users", users.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", users.getTotalPages());

		return "admin-browse-users";
	}
	
	@RequestMapping(value = "/admin/users/searchByStatus/{status}", method = RequestMethod.GET)
	public String searchUsersByStatus(@PathVariable boolean status, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<AppUser> users = userService.findByStatus(status, page);
		model.addAttribute("users", users.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", users.getTotalPages());

		return "admin-browse-users";
	}
	
	@RequestMapping(value = "/admin/credentials/searchAll", method = RequestMethod.GET)
	public String searchAllCredentials(Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findAll(page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		return "admin-browse-credentials";
	}
	
	@RequestMapping(value = "/admin/credentials/searchByCompany/{company}", method = RequestMethod.GET)
	public String searchByCompany(@PathVariable String company, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByCompany(company, page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		return "admin-browse-credentials";
	}
	
	@RequestMapping(value = "/admin/credentials/searchByEnv/{env}", method = RequestMethod.GET)
	public String searchByEnv(@PathVariable String env, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByEnvironment(env, page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		return "admin-browse-credentials";
	}
	
	@RequestMapping(value = "/admin/credentials/searchByDesc/{desc}", method = RequestMethod.GET)
	public String searchByDesc(@PathVariable String desc, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByDesc(desc, page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		return "admin-browse-credentials";
	}
	
	@RequestMapping(value = "/admin/credentials/searchByModifier/{modifier}", method = RequestMethod.GET)
	public String searchByModifier(@PathVariable String modifier, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByModifier(modifier, page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());

		return "admin-browse-credentials";
	}
	
	@RequestMapping(value = "/admin/contacts/searchAll", method = RequestMethod.GET)
	public String searchAllContacts(Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Contact> contacts = contactService.findAll(page);
		model.addAttribute("contacts", contacts.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", contacts.getTotalPages());

		return "admin-browse-contacts";
	}
	
	@RequestMapping(value = "/admin/contacts/searchByName/{name}", method = RequestMethod.GET)
	public String searchByName(@PathVariable String name, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Contact> contacts = contactService.findByName(name, page);
		model.addAttribute("contacts", contacts.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", contacts.getTotalPages());

		return "admin-browse-contacts";
	}
	
	@RequestMapping(value = "/admin/contacts/searchByEmail/{email}", method = RequestMethod.GET)
	public String searchContactsByEmail(@PathVariable String email, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Contact> contacts = contactService.findByEmail(email, page);
		model.addAttribute("contacts", contacts.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", contacts.getTotalPages());

		return "admin-browse-contacts";
	}
	
	@RequestMapping(value = "/admin/contacts/searchByStatus/{status}", method = RequestMethod.GET)
	public String searchByStatus(@PathVariable String status, Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Contact> contacts = contactService.findByStatus(status, page);
		model.addAttribute("contacts", contacts.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", contacts.getTotalPages());

		return "admin-browse-contacts";
	}
	
	@RequestMapping(value = "/admin/contact-edit/{id}", method = RequestMethod.GET)
	public String editContact(@PathVariable Integer id, Model model) {
		Contact contact = contactService.findOne(id);
		model.addAttribute("contact", contact);
		return "contact-edit";
	}
	
	@RequestMapping(value = "/admin/contact-reply/{id}", method = RequestMethod.GET)
	public String replyContact(@PathVariable Integer id, Model model) {
		Contact contact = contactService.findOne(id);
		contact.setMessage("");
		model.addAttribute("contact", contact);
		return "contact-reply";
	}
	
	@RequestMapping(value = "/admin/contact-edit/{id}", method = RequestMethod.POST)
	public String saveEditContact(@ModelAttribute("contact") Contact contact, @PathVariable Integer id) {
		contactService.updateContact(id, contact);
		return "redirect:/admin/browse-contacts.html";
	}
	
	@RequestMapping(value = "/admin/contact-reply/{id}", method = RequestMethod.POST)
	public String replyContactSubmit(@ModelAttribute("contact") Contact contact, @PathVariable Integer id) {
		String emailAddress = contact.getEmail();
		String name = contact.getName();
		String message = contact.getMessage();
		userService.emailUser(name, emailAddress, message);
		return "redirect:/admin/browse-contacts.html";
	}
	
	@RequestMapping(value = "/admin/contacts/search", method = RequestMethod.POST)
	public String searchContactsSubmit(
			@ModelAttribute("searchTermBackingBean") SearchTermBackingBean searchTermBackingBean, Model model) {
		String searchTerm = searchTermBackingBean.getSearchTerm();
		if(searchTerm == null || searchTerm.isEmpty()) {
			PageRequest page = new PageRequest(0, PAGE_SIZE);
			Page<Contact> contacts = contactService.findAll(page);
			model.addAttribute("contacts", contacts.getContent());
			model.addAttribute("pageNo", 1);
			model.addAttribute("pageMax", contacts.getTotalPages());
		} else {
			List<Contact> contacts = contactService.findByName(searchTerm);
			model.addAttribute("contacts", contacts);
			model.addAttribute("pageNo", 0);
			model.addAttribute("pageMax", -1);
		}

		return "admin-browse-contacts";
	}
	
}
