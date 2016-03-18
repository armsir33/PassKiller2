package se.midport.service;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import se.midport.entity.AppUser;
import se.midport.entity.Role;
import se.midport.repository.RoleRepository;
import se.midport.repository.UserRepository;

@Service
@Transactional
public class InitDbService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	public void init() {
		List<AppUser> users = userRepository.findByUsername("admin");
		if(users.isEmpty()) {
			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);
			
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleAdmin);
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			AppUser adminUser = new AppUser();
			adminUser.setUsername("admin");
			adminUser.setEmail("zni@midportscandinavia.com");
			adminUser.setPassword(encoder.encode("admin"));
			adminUser.setRoles(roles);
			adminUser.setEnabled(true);
			userRepository.save(adminUser);
		}
	}
}
