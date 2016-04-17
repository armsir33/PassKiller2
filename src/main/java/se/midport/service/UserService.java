package se.midport.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import se.midport.entity.AppUser;
import se.midport.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private MailSender mailSender;
    
	@Autowired
	private SimpleMailMessage templateMessage;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<AppUser> findAll() {
		return userRepository.findAll();
	}

	public void save(AppUser appUser) {
		userRepository.save(appUser);
	}

	public List<AppUser> findByUsername(String username) {
		return userRepository.findByUsernameContainingIgnoreCase(username);
	}

	public Page<AppUser> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public void delete(Integer id) {
		userRepository.delete(id);
	}

	public void updateByUsername(AppUser appUser) {
		List<AppUser> users = userRepository.findByUsername(appUser.getUsername());
		if(!users.isEmpty()) {
			AppUser fetchedUser = users.get(0);
			fetchedUser.setAddress(appUser.getAddress());
			fetchedUser.setCity(appUser.getCity());
			fetchedUser.setCompany(appUser.getCompany());
			fetchedUser.setFirstName(appUser.getFirstName());
			fetchedUser.setLastName(appUser.getLastName());
			fetchedUser.setPhone(appUser.getPhone());
			fetchedUser.setState(appUser.getState());
			fetchedUser.setTitle(appUser.getTitle());
			fetchedUser.setWebsite(appUser.getWebsite());
			fetchedUser.setZip(appUser.getZip());
			fetchedUser.setPassword(appUser.getPassword());
			userRepository.saveAndFlush(fetchedUser);
		}
	}

	public AppUser findOneByUsername(String username) {
		return userRepository.findByUsername(username).get(0);
	}

	public AppUser findOne(Integer id) {
		return userRepository.findOne(id);
	}

	public boolean checkIfUsernameExist(String username) {
		boolean ret = false;
		List<AppUser> users = userRepository.findByUsername(username);
		if(users == null || users.isEmpty())
			ret = false;
		else
			ret = true;
		return ret;
	}

	public void updateActivateStatus(Integer id) {
		AppUser appUser = userRepository.findOne(id);
		appUser.setEnabled(true);
		userRepository.saveAndFlush(appUser);
	}

	public void email(String username, String email) {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(email);
        msg.setText(
            "Dear " + username
                + "\r\n\r\nWelcome to Midport PassKiller \r\nYour account is activated now.\r\n\r\nBest Wishes\r\n\r\nPassKiller Management Team");
        
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
	}

	public void emailAdmin(String username) {
		AppUser admin = userRepository.findByUsername("admin").get(0);
		String email = admin.getEmail();
		
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(email);
        msg.setText(
            "Dear Admin, \r\n\r\nA new user " + username +" is registered. Please check identity and decide whether activate.\r\n\r\nBest Wishes\r\n\r\nPassKiller Management Team");
        
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
	}

	public void updateDeactivateStatus(Integer id) {
		AppUser appUser = userRepository.findOne(id);
		appUser.setEnabled(false);
		userRepository.saveAndFlush(appUser);
	}

	public void emailUser(String name, String emailAddress, String message) {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(emailAddress);
        msg.setText(
            "Dear "+name+"\r\n\r\n"+message+"\r\n\r\nBest Wishes\r\n\r\nPassKiller Management Team");
        
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
	}

	public Page<AppUser> findByUsername(String username, Pageable pageable) {
		return userRepository.findByUsernameContainingIgnoreCase(username, pageable);
	}

	public Page<AppUser> findByFirstName(String firstName, Pageable pageable) {
		return userRepository.findByFirstNameContainingIgnoreCase(firstName, pageable);
	}

	public Page<AppUser> findByLastName(String lastName, Pageable pageable) {
		return userRepository.findByLastNameContainingIgnoreCase(lastName,pageable);
	}

	public Page<AppUser> findByEmail(String email, Pageable pageable) {
		return userRepository.findByEmailContainingIgnoreCase(email, pageable);
	}

	public Page<AppUser> findByStatus(boolean status, Pageable pageable) {
		return userRepository.findByEnabledContainingIgnoreCase(status, pageable);
	}
	
}
