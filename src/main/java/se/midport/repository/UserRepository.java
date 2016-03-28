package se.midport.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import se.midport.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer>{

	List<AppUser> findByUsernameContainingIgnoreCase(String username);

	List<AppUser> findByUsername(String username);

	Page<AppUser> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

	Page<AppUser> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

	Page<AppUser> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);

	Page<AppUser> findByEmailContainingIgnoreCase(String email, Pageable pageable);

	Page<AppUser> findByEnabledContainingIgnoreCase(boolean enabled, Pageable pageable);

}
