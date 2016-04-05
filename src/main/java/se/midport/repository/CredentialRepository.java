package se.midport.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se.midport.entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer>{

	List<Credential> findByNameContainingIgnoreCase(String name);

	Page<Credential> findByModifierContainingIgnoreCase(String modifier, Pageable pageable);

	Page<Credential> findByRange(String range, Pageable pageable);

	Page<Credential> findByCompanyContainingIgnoreCase(String company, Pageable pageable);

	Page<Credential> findByEnvironmentContainingIgnoreCase(String environment, Pageable pageable);

	Page<Credential> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);

	Page<Credential> findByCompanyContainingIgnoreCaseAndRange(String company, String range, Pageable pageable);

	Page<Credential> findByEnvironmentContainingIgnoreCaseAndRange(String environment, String range, Pageable pageable);

	Page<Credential> findByDescriptionContainingIgnoreCaseAndRange(String description, String range, Pageable pageable);

	Page<Credential> findByModifierContainingIgnoreCaseAndRange(String modifier, String range, Pageable pageable);


}
