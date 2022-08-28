package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.entity.Orders;

public interface OrderRepository  extends PagingAndSortingRepository<Orders, Long>, JpaSpecificationExecutor<Orders>{
	@Override
	List<Orders> findAll();
}
