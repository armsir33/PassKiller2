package se.midport.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.midport.entity.Contact;
import se.midport.repository.ContactRepository;

@Service
@Transactional
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	public void save(Contact contact) {
		contactRepository.save(contact);
	}

	public Page<Contact> findAll(Pageable pageable) {
		return contactRepository.findAll(pageable);
	}

	public Contact findOne(Integer id) {
		return contactRepository.findOne(id);
	}

	public void updateContact(Integer id, Contact contact) {
		Contact dbContact = contactRepository.findOne(id);
		dbContact.setStatus(contact.getStatus());
		contactRepository.saveAndFlush(dbContact);		
	}

	public List<Contact> findByName(String name) {
		return contactRepository.findByNameContainingIgnoreCase(name);
	}

	public void delete(Integer id) {
		contactRepository.delete(id);
	}

	
}
