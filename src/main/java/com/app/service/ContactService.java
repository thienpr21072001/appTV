package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.entity.Contact;
import com.app.model.PagingSearchFilterContact;

public interface ContactService {
	Contact createContact(Contact user);
	Contact updateContact(Contact user);
	void deleteContact(Contact user);
	Contact getById(long id);
	 
	List<Contact> getAll();
	Page<Contact> getAll(PagingSearchFilterContact searchFilterUser);
	
	
}
