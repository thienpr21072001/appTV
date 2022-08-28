package com.app.model;

import java.math.BigDecimal;

public class OrderDetailResponse {
	private long id;
	private BigDecimal price;	
	private BigDecimal subPrice;
	private	long proId;
	private String proName;
	private long orderId;
	private int quantity;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public long getProId() {
		return proId;
	}
	public void setProId(long proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getSubPrice() {
		return subPrice;
	}
	public void setSubPrice(BigDecimal subPrice) {
		this.subPrice = subPrice;
	}
	
	
	
}
