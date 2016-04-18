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
import se.midport.entity.Credential;
import se.midport.form.SearchTermBackingBean;
import se.midport.service.CredentialService;

@Controller
public class CredentialController {
	private static final int PAGE_SIZE = 5;

	@Autowired
	private CredentialService credentialService;

	@ModelAttribute("searchTermBackingBean")
	public SearchTermBackingBean getSearchTermBackingBean() {
		return new SearchTermBackingBean();
	}

	@RequestMapping(value = "/credentials", method = RequestMethod.GET)
	public String getpass(Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByRange("Public", page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("key", "none");
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());
		return "credentials";
	}
	
	@RequestMapping(value = "/credentials", method = RequestMethod.POST)
	public String getpass4post() {
		return "redirect:/credentials";
	}

	@RequestMapping(value = "/credentials/{pageNo}", method = RequestMethod.GET)
	public String getpass(@PathVariable Integer pageNo, Model model) {
		PageRequest page = new PageRequest(pageNo - 1, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByRange("Public", page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("key", "none");
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageMax", credentials.getTotalPages());
		System.out.println(credentials.getTotalPages());
		return "credentials";
	}

	@RequestMapping(value = "/credentials/search", method = RequestMethod.GET)
	public String searchPasswordForm(Model model) {
		return "credentials";
	}

	@RequestMapping(value = "/credentials/search", method = RequestMethod.POST)
	public String searchPasswordSubmit(
			@ModelAttribute("searchTermBackingBean") SearchTermBackingBean searchTermBackingBean, Model model) {
		String searchTerm = searchTermBackingBean.getSearchTerm();
		if (searchTerm.isEmpty()) {
			return this.getpass(model);
		} else {
			List<Credential> credentials = credentialService.findByName("%" + searchTerm + "%");
			model.addAttribute("credentials", credentials);
			model.addAttribute("pageNo", 0);
			model.addAttribute("pageMax", -2);
			return "credentials";
		}
	}

	@RequestMapping(value = "/credentials/searchAll", method = RequestMethod.GET)
	public String getAllCredentials(Model model) {
		PageRequest page = new PageRequest(0, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findAll(page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("key", "none");
		model.addAttribute("pageNo", 1);
		model.addAttribute("pageMax", credentials.getTotalPages());
		return "credentials";
	}

	@RequestMapping(value = "/credentials/searchByCompany/{pageNo}/{company}", method = RequestMethod.GET)
	public String searchCredentialsByCompany(@PathVariable Integer pageNo, @PathVariable String company, Model model) {
		PageRequest page = new PageRequest(pageNo - 1, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByCompanyAndRange(company, "Public", page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("key", "company");
		model.addAttribute("company", company);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageMax", credentials.getTotalPages());
		return "credentials";
	}

	@RequestMapping(value = "/credentials/searchByEnv/{pageNo}/{env}", method = RequestMethod.GET)
	public String searchCredentialsByEnv(@PathVariable Integer pageNo, @PathVariable String env, Model model) {
		PageRequest page = new PageRequest(pageNo - 1, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByEnvironmentAndRange(env, "Public", page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("key", "env");
		model.addAttribute("env", env);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageMax", credentials.getTotalPages());
		return "credentials";
	}

	@RequestMapping(value = "/credentials/searchByDesc/{pageNo}/{desc}", method = RequestMethod.GET)
	public String searchCredentialsByDesc(@PathVariable Integer pageNo, @PathVariable String desc, Model model) {
		PageRequest page = new PageRequest(pageNo - 1, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByDescAndRange(desc, "Public", page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("key", "desc");
		model.addAttribute("desc", desc);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageMax", credentials.getTotalPages());
		return "credentials";
	}

	@RequestMapping(value = "/credentials/searchByModifier/{pageNo}/{modifier}", method = RequestMethod.GET)
	public String searchCredentialsByModifier(@PathVariable Integer pageNo, @PathVariable String modifier,
			Model model) {
		PageRequest page = new PageRequest(pageNo - 1, PAGE_SIZE);
		Page<Credential> credentials = credentialService.findByModifierAndRange(modifier, "Public", page);
		model.addAttribute("credentials", credentials.getContent());
		model.addAttribute("key", "modifier");
		model.addAttribute("modifier", modifier);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageMax", credentials.getTotalPages());
		return "credentials";
	}
}
