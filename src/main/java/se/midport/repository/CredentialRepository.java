package se.midport.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se.midport.entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer>{

	List<Credential> findByNameContainingIgnoreCase(String name);

	Page<Credential> findByModifier(String modifier, Pageable pageable);

	Page<Credential> findByRange(String range, Pageable pageable);

}
