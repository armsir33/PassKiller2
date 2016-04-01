package se.midport.service;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	public Page<Credential> findByModifier(String modifier, Pageable page) {
		return credentialRepository.findByModifierContainingIgnoreCase(modifier, page);
	}

	public Page<Credential> findByRange(String range, Pageable pageable) {
		return credentialRepository.findByRange(range, pageable);
	}

	public Credential findOne(Integer id) {
		return credentialRepository.findOne(id);
	}

	public void updateCredential(Integer id, Credential credential) {
		Credential dbCredential = credentialRepository.findOne(id);
		dbCredential.setCompany(credential.getCompany());
		dbCredential.setDate(new Date());
		dbCredential.setDescription(credential.getDescription());
		dbCredential.setEnvironment(credential.getEnvironment());
		dbCredential.setName(credential.getName());
		dbCredential.setPassword(credential.getPassword());
		dbCredential.setRange(credential.getRange());
		credentialRepository.saveAndFlush(dbCredential);
	}

	public List<Credential> findAll() {
		return credentialRepository.findAll();
	}

	public Page<Credential> findByCompany(String company, Pageable pageable) {
		return credentialRepository.findByCompanyContainingIgnoreCase(company, pageable);
	}

	public Page<Credential> findByEnvironment(String environment, Pageable pageable) {
		return credentialRepository.findByEnvironmentContainingIgnoreCase(environment, pageable);
	}

	public Page<Credential> findByDesc(String description, Pageable pageable) {
		return credentialRepository.findByDescriptionContainingIgnoreCase(description, pageable);
	}

	public Page<Credential> findByCompanyAndRange(String company, String range, Pageable pageable) {
		return credentialRepository.findByCompanyAndRangeContainingAllIgnoreCase(company, range, pageable);
	}

	public Page<Credential> findByEnvironmentAndRange(String environment, String range, Pageable pageable) {
		return credentialRepository.findByEnvironmentAndRangeContainingAllIgnoreCase(environment, range, pageable);
	}

	public Page<Credential> findByDescAndRange(String desc, String range, Pageable pageable) {
		return credentialRepository.findByDescriptionAndRangeContainingAllIgnoreCase(desc, range, pageable);
	}

	public Page<Credential> findByModifierAndRange(String modifier, String range, Pageable pageable) {
		return credentialRepository.findByModifierContainingAllIgnoreCase(modifier, range, pageable);
	}

}
