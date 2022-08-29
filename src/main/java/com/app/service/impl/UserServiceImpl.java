package com.app.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.model.PagingSearchFilterUser;
import com.app.model.entity.UserSpecification;
import com.app.repository.UserRepository;
import com.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepo;
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		user.setStatus(1);
		 
		return userRepo.save(user);
	}
 

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		user.setUpdateDate(new Date());
		user.setStatus(1);
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		//productRepo.delete(products);
		user.setStatus(0);
		userRepo.save(user);
	}

	@Override
	public User getById(long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public User getByName(String name) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(name);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public Page<User> getAll(PagingSearchFilterUser psfp) {
		// TODO Auto-generated method stub
		return userRepo.findAll(new UserSpecification(psfp.getKeyword()), PageRequest.of(psfp.getPage(), psfp.getPageSize()));
	}
	 
	

}
