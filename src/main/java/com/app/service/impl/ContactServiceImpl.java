package com.app.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.entity.Contact;
import com.app.model.PagingSearchFilterContact;
import com.app.model.entity.ContactSpecification;
import com.app.repository.ContactRepository;
import com.app.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{
	@Autowired
	ContactRepository userRepo;
	
	private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);


	@Override
	public Contact createContact(Contact contact) {
		// TODO Auto-generated method stub
		contact.setCreateDate(new Date());
		contact.setUpdateDate(new Date());
		contact.setStatus(1);
		return userRepo.save(contact);
	}
 

	@Override
	public Contact updateContact(Contact contact) {
		// TODO Auto-generated method stub
		contact.setUpdateDate(new Date());
		contact.setStatus(1);
		return userRepo.save(contact);
	}

	@Override
	public void deleteContact(Contact contact) {
		// TODO Auto-generated method stub
		//productRepo.delete(products);
		contact.setStatus(0);
		userRepo.save(contact);
	}

	@Override
	public Contact getById(long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElse(null);
	}
 

	@Override
	public List<Contact> getAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public Page<Contact> getAll(PagingSearchFilterContact psfp) {
		// TODO Auto-generated method stub
		return userRepo.findAll(new ContactSpecification(psfp.getKeyword()), PageRequest.of(psfp.getPage(), psfp.getPageSize()));
	}
	 
	

}
