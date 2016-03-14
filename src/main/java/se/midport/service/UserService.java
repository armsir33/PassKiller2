package se.midport.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.midport.entity.AppUser;
import se.midport.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
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
		System.out.println("ddddddd " + appUser.getFirstName());
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
			userRepository.saveAndFlush(fetchedUser);
		}
	}

	public AppUser findOneByUsername(String username) {
		return userRepository.findByUsername(username).get(0);
	}

	public AppUser findOne(Integer id) {
		return userRepository.findOne(id);
	}

}
