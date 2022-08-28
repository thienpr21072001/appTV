package com.app.model;

public class CartItemModel {
	private long id;
	private int count;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CartItemModel [id=" + id + ", count=" + count + "]";
	}
	 

}
