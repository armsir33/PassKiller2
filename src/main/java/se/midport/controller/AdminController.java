package se.midport.controller;

import java.util.Date;
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
public class AdminController {
	
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
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getpass(Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findAll(page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());
		
		Page<AppUser> users = userService.findAll(page);
		model.addAttribute("users", users.getContent());
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", users.getTotalPages());
		return "admin";
	}

	@RequestMapping(value = "/admin/{pageNo}", method = RequestMethod.GET)
	public String getpass(@PathVariable Integer pageNo, Model model) {
		PageRequest page = new PageRequest(pageNo - 1, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findAll(page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageMax", credentials.getTotalPages());
		return "admin";
	}

	@RequestMapping(value="/admin/search", method=RequestMethod.GET)
    public String searchPasswordForm(Model model) {
        return "admin";
    }
	
	@RequestMapping(value = "/admin/search", method = RequestMethod.POST)
	public String searchPasswordSubmit(@ModelAttribute("searchTermBackingBean") SearchTermBackingBean searchTermBackingBean, Model model) {
		String searchTerm = searchTermBackingBean.getSearchTerm();
		if(searchTerm == null || searchTerm.isEmpty()) {
			return this.getpass(model);
		} else {
			List<Credential> credentials = credentialService.findByName(searchTerm);
			model.addAttribute("credentials", credentials);
			model.addAttribute("pageNo", 0);
			model.addAttribute("pageMax", -1);
			model.addAttribute("credential-tab-status", "active");
			
			PageRequest page = new PageRequest(0, PAGE_SIZE);
			Page<AppUser> users = userService.findAll(page);
			model.addAttribute("users", users.getContent());
			model.addAttribute("pageNo", 1);
			model.addAttribute("pageMax", users.getTotalPages());
			model.addAttribute("user-tab-status", "");
			
			return "admin";
		}
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String addCredential(@ModelAttribute("credential") Credential credential, Model model) {
		credential.setDate(new Date());
		credentialService.save(credential);
		return "redirect:/admin.html";
	}
	
	@RequestMapping(value = "/admin/remove/{id}", method = RequestMethod.GET)
	public String removeCredential(@PathVariable Integer id, Model model) {
		credentialService.delete(id);
		return "redirect:/admin.html";
	}
	
	
}
