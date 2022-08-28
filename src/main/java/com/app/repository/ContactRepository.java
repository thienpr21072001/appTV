package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Contact;

@Repository
public interface ContactRepository  extends PagingAndSortingRepository<Contact, Long>, JpaSpecificationExecutor<Contact>{

	@Override
	List<Contact> findAll();
	 
 
}
