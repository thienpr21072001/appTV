package com.app.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.entity.Orders;
import com.app.model.PagingSearchFilterOrder;
import com.app.model.entity.OrderSpecification;
import com.app.repository.OrderRepository;
import com.app.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderRepository userRepo;
	
	private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);


	@Override
	public Orders createOrder(Orders orders) {
		// TODO Auto-generated method stub
		orders.setCreateDate(new Date());
		orders.setUpdateDate(new Date());
		orders.setStatus(1);
		return userRepo.save(orders);
	}
 

	@Override
	public Orders updateOrder(Orders contact) {
		// TODO Auto-generated method stub
		contact.setUpdateDate(new Date());
		contact.setStatus(1);
		return userRepo.save(contact);
	}

	@Override
	public void deleteOrder(Orders contact) {
		// TODO Auto-generated method stub
		//productRepo.delete(products);
		contact.setStatus(0);
		userRepo.save(contact);
	}

	@Override
	public Orders getById(long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElse(null);
	}
 

	@Override
	public List<Orders> getAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public Page<Orders> getAll(PagingSearchFilterOrder psfp) {
		// TODO Auto-generated method stub
		return userRepo.findAll(new OrderSpecification(psfp.getKeyword(), psfp.getProvinceId(), psfp.getApprovedStep()), PageRequest.of(psfp.getPage(), psfp.getPageSize()));
	}
	 
}
