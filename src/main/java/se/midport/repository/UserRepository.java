package se.midport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.midport.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer>{

	List<AppUser> findByUsernameContainingIgnoreCase(String username);

}
