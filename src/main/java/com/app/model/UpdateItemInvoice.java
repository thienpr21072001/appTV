package com.app.model;

public class UpdateItemInvoice {
	private long id;
	private int quantity;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "UpdateItemInvoice [id=" + id + ", quantity=" + quantity + "]";
	}

	
	
	
}



