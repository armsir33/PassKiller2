package se.midport.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import botdetect.web.Captcha;
import se.midport.entity.Contact;
import se.midport.service.ContactService;

@Controller
public class IndexController {
	
	@Autowired
	private ContactService contactService;
	
	@ModelAttribute("contact")
	public Contact construct() {
		return new Contact();
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/docs")
	public String docs() {
		return "docs";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactForm() {
		return "contact";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String contactSubmit(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "contact";
		} else {
			Captcha captcha = Captcha.load(request, "captchaCodeTextBox");
			boolean isHuman = captcha.validate(request, contact.getStatus());
			if (isHuman) {
				contact.setStatus("Unresolved");
				contactService.save(contact);
				return "redirect:/contact.html?success=true";
			} else {
				
				return "redirect:/contact.html?failed=true";
			}
		}
	}
}
