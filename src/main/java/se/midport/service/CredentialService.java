package se.midport.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.midport.entity.Credential;
import se.midport.repository.CredentialRepository;

@Service
@Transactional
public class CredentialService {

	@Autowired
	private CredentialRepository credentialRepository;

	public Page<Credential> findAll(Pageable pageable) {
		return credentialRepository.findAll(pageable);
	}
	
	public List<Credential> findByName(String name) {
		return credentialRepository.findByNameContainingIgnoreCase(name);
	}

	public Credential save(Credential credential) {
		return credentialRepository.save(credential);
	}
	
	public void delete(Integer id) {
		credentialRepository.delete(id);
	}
}
