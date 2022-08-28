/**
 * 
 */
package com.app.utils;
 
public enum ProvinceUtil {
	HA_NOI(1, "Hà nội"),
	SAPA(2, "Sa Pa"),
	VINH_HA_LONG(3, "Vịnh hạ long"),
	HUE(4, "Huế"),
	DA_NANG(5, "Đà nẵng"),
	PHU_YEN(6, "Phú yên"),
	NHA_TRANG(7,"Nha trang"),
	QUANG_NAM(8, "Quảng nam"),
	DA_LAT(9, "Đà lạt"),
	CON_DAO(10, "Côn đảo"),
	PHAN_THIET(11, "Phan thiết");
	
	private int value;
	private String text;
 
	private ProvinceUtil(int value, String text) {
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
