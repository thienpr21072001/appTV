package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Product;

@Repository
public interface ProductRepository  extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product>{

	@Override
	List<Product> findAll();
	
	Product findByName(String name);
 
}
