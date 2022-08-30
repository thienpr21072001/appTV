package com.app.model.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.entity.Orders;
import com.app.entity.Product;
import com.app.entity.User;

public class OrderSpecification implements Specification<Orders>{
	private final String searchKey;
	private final Integer provinceId;
	private final Integer approvedStep;
	private final Long userId;

	public OrderSpecification(String searchKey, Integer provinceId, Integer approvedStep,  Long userId) {
		this.searchKey = searchKey;
		this.provinceId = provinceId;
		this.approvedStep = approvedStep;
		this.userId = userId;
	}

	@Override
	public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new LinkedList<>();
	 
		if(provinceId != null) {
			Join<Product, Product> wareProduct  =  root.join("products", JoinType.INNER); //entity
			Predicate preProvinceId = criteriaBuilder.equal(wareProduct.get("provinceId"), provinceId);
			predicates.add(preProvinceId);
		}
		 
  
		if(approvedStep != null) {
			Predicate preApprovedStep = criteriaBuilder.equal(root.get("approvedStep"), approvedStep);
			predicates.add(preApprovedStep);
		}
 
		if(userId != null) {
			Join<User, User> userInnner  =  root.join("user", JoinType.INNER); //entity
			Predicate preUser = criteriaBuilder.equal(userInnner.get("id"), userId);
			predicates.add(preUser);
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
	}

	public String getSearchKey() {
		return searchKey;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

 

	public Integer getApprovedStep() {
		return approvedStep;
	}

	public Long getUserId() {
		return userId;
	}
 

	 

}
