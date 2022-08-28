/**
 * 
 */
package com.app.utils;

 
public enum ApprovedStep {
	IN_PROGRESS(0, "Đang xử lý"),
	COMPLETE(1, "Đã hoàn thành"),
	CANCEL(2, "Đã huỷ");
	
	private int value;
	private String text;
 
	private ApprovedStep(int value, String text) {
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
