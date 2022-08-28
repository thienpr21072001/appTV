/**
 * 
 */
package com.app.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.app.utils.AuthUtils;

@Component
public class RequestFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if(!isLoginURI(req.getRequestURI())) {
	        HttpSession session = req.getSession();
			boolean isLogin = AuthUtils.isLogin(session);
			if(!isLogin) {
				resp.sendRedirect("/login");
			}
		}
		chain.doFilter(request, response);
		 
	}
	
	
	
	public boolean isLoginURI(String uri) {
		if(uri.contains("login")) {
			return true;
		}
		return false;
	}

}
