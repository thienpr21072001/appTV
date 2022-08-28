/**
 * 
 */
package com.app.utils;

import javax.servlet.http.HttpSession;

public class AuthUtils {

	public static boolean isLogin(HttpSession session) {
		String username = (String) session.getAttribute(Constant.USER_INFO);
		if(!isNullOrEmpty(username)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNullOrEmpty(String value) {
		return value == null ||	value.trim().isEmpty();
	}
}
