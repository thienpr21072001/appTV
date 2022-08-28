package com.app.model.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.entity.Product;

public class ProductSpecification implements Specification<Product>{
	private final String searchKey;

	public ProductSpecification(String searchKey ) {
		this.searchKey = searchKey;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new LinkedList<>();
		if(searchKey!=null && !searchKey.trim().isEmpty()) {
			String key = "%"+searchKey.trim()+"%";
			Predicate prediName = criteriaBuilder.like(root.get("name"), key);
			predicates.add(prediName);
		}
		 
		Predicate predicateStatus = criteriaBuilder.equal(root.get("status"), 1);
		predicates.add(predicateStatus);
		return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
	}

	public String getSearchKey() {
		return searchKey;
	}

	 

}
