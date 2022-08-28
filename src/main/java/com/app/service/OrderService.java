package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.entity.Orders;
import com.app.model.PagingSearchFilterOrder;

public interface OrderService {
	Orders createOrder(Orders user);
	Orders updateOrder(Orders user);
	void deleteOrder(Orders user);
	Orders getById(long id);
	 
	List<Orders> getAll();
	Page<Orders> getAll(PagingSearchFilterOrder searchFilterUser);
}
