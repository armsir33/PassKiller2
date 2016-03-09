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

}
