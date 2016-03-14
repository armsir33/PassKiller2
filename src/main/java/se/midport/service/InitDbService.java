package se.midport.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import se.midport.entity.AppUser;
import se.midport.entity.Credential;
import se.midport.entity.Role;
import se.midport.repository.CredentialRepository;
import se.midport.repository.RoleRepository;
import se.midport.repository.UserRepository;

@Service
@Transactional
public class InitDbService {
	
	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private CredentialRepository credentialRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		AppUser adminUser = new AppUser();
		adminUser.setUsername("admin");
		adminUser.setEmail("zni@midportscandinavia.com");
		adminUser.setPassword(encoder.encode("admin"));
		adminUser.setRoles(roles);
		adminUser.setEnabled(true);
		UserRepository.save(adminUser);
		
		List<Role> testRoles = new ArrayList<Role>();
		testRoles.add(roleUser);
		
		AppUser testUser = new AppUser();
		testUser.setUsername("test");
		testUser.setEmail("test@midportscandinavia.com");
		testUser.setPassword(encoder.encode("test"));
		testUser.setRoles(testRoles);
		testUser.setEnabled(true);
		UserRepository.save(testUser);
		
		SimpleDateFormat format = new SimpleDateFormat();
		
		Credential ionCredential = new Credential();
		ionCredential.setName("ION");
		ionCredential.setPassword("IONPass");
		Date ionCurDate = new Date();
		format.format(ionCurDate);
		ionCredential.setDate(ionCurDate);
		ionCredential.setRange("Public");
		credentialRepository.save(ionCredential);
		
		Credential lnCredential = new Credential();
		lnCredential.setName("LN");
		lnCredential.setPassword("LNPass");
		Date lnCurDate = new Date();
		format.format(lnCurDate);
		lnCredential.setDate(lnCurDate);
		lnCredential.setRange("Public");
		credentialRepository.save(lnCredential);
		
		Credential worktopCredential = new Credential();
		worktopCredential.setName("Worktop");
		worktopCredential.setPassword("WorktopPass");
		Date wkCurDate = new Date();
		format.format(wkCurDate);
		worktopCredential.setDate(wkCurDate);
		worktopCredential.setRange("Public");
		credentialRepository.save(worktopCredential);
	}
}
