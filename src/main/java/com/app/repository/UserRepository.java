package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.User;

@Repository
public interface UserRepository  extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User>{

	@Override
	List<User> findAll();
	
	User findByUsername(String username);
 
}
