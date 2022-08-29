/**
 * 
 */
package com.app.utils;
 
public enum RoleUtil {
	ADMIN(1, "Người quản lý"),
	USER(2, "Người dùng");
 
	
	private int value;
	private String text;
 
	private RoleUtil(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
