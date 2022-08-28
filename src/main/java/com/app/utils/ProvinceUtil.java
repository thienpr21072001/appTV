/**
 * 
 */
package com.app.utils;
 
public enum ProvinceUtil {
	HA_NOI(1, "Hà nội", 1),
	SAPA(2, "Sa Pa", 1),
	VINH_HA_LONG(3, "Vịnh hạ long", 1),
	HUE(4, "Huế", 2),
	DA_NANG(5, "Đà nẵng", 2),
	PHU_YEN(6, "Phú yên", 2),
	NHA_TRANG(7,"Nha trang", 2),
	QUANG_NAM(8, "Quảng nam", 2),
	DA_LAT(9, "Đà lạt", 3),
	CON_DAO(10, "Côn đảo", 3),
	PHAN_THIET(11, "Phan thiết", 3);
	
	private int value;
	private String text;
	private int area;
 
	private ProvinceUtil(int value, String text, int area) {
		this.value = value;
		this.text = text;
		this.area = area;
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
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	
	

}
