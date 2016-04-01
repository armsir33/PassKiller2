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

	Page<Credential> findByCompanyAndRangeContainingAllIgnoreCase(String company, String range, Pageable pageable);

	Page<Credential> findByEnvironmentAndRangeContainingAllIgnoreCase(String environment, String range, Pageable pageable);

	Page<Credential> findByDescriptionAndRangeContainingAllIgnoreCase(String description, String range, Pageable pageable);

	Page<Credential> findByModifierContainingAllIgnoreCase(String modifier, String range, Pageable pageable);

}
