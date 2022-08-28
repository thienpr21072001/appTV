package com.app.utils;

public class Constant {
	//public final static String PATH_UPLOAD_IMAGES = "C:\\Users\\Admin\\git\\orderfood-springboot\\OrderFood\\src\\main\\resources\\static\\upload\\";
	public final static String PATH_UPLOAD_IMAGES = "/Users/tamnc/Documents/Local/ProjectExample/AppTV/src/main/resources/static/upload/";
	public final static String REGEX_PHONE = "^0\\d{8,11}$";
	public final static String REGEX_PASSWORD = "\\b(?=.*\\d)+[A-Za-z\\d]{4,16}\\b";
	public final static String REGEX_USERNAME = "\\b[A-Za-z\\d_-]{6,16}\\b";
	public final static String REGEX_PRICE = "\\d{4,12}$";
	public final static String REGEX_NAME_PRODUCT = "^[A-Z a-z\\dÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]{2,16}$";
	public final static String SESSION_INVOICE = "invoices";
	
	public static final String MSG_ERROR = "msgError";
	public static final String MSG_SUCCESS = "msgSuccess";
	public static final String USER_INFO = "userInfo";
 
}
