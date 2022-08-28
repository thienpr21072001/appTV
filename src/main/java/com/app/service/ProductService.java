package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.entity.Product;
import com.app.model.PagingSearchFilterProduct;

public interface ProductService {
	Product createProduct(Product products);
	Product updateProduct(Product products);
	void deleteProducts(Product products);
	Product getById(long id);
	Product getByName(String name);
	List<Product> getAll();
	Page<Product> getAll(PagingSearchFilterProduct searchFilterProduct);
	
	
}
