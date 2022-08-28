package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.entity.User;
import com.app.model.PagingSearchFilterUser;

public interface UserService {
	User createUser(User user);
	User updateUser(User user);
	void deleteUser(User user);
	User getById(long id);
	User getByName(String name);
	List<User> getAll();
	Page<User> getAll(PagingSearchFilterUser searchFilterUser);
	
	
}
