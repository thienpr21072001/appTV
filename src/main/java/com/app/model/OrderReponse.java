package com.app.model;

import java.math.BigDecimal;
import java.util.List;

public class OrderReponse {
	private long id;
	private BigDecimal totalPrice ;
	private List<OrderDetailResponse> orderDetails;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getTotalPrice() {
 
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<OrderDetailResponse> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailResponse> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
