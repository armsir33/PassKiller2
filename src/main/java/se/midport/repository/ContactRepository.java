package se.midport.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se.midport.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

	List<Contact> findByNameContainingIgnoreCase(String name);

	Page<Contact> findByNameContainingIgnoreCase(String name, Pageable pageable);

	Page<Contact> findByStatus(String status, Pageable pageable);

	Page<Contact> findByEmailContainingIgnoreCase(String email, Pageable pageable);

}
