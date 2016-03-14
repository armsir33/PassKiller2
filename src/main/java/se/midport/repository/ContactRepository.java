package se.midport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import se.midport.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

	List<Contact> findByNameContainingIgnoreCase(String name);

}
