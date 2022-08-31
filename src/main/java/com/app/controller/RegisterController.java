/**
 * 
 */
package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.entity.User;
import com.app.service.UserService;
import com.app.utils.Constant;
import com.app.validator.UserValidator;
@Controller
public class RegisterController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserValidator userValidator;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	 
	@InitBinder
	public void dataBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == User.class) {
			binder.setValidator(userValidator);
		}
	}
	
	
	@GetMapping(value = {"/dang-ky"})
	public String dangky(HttpSession session, ModelMap map) {
		map.addAttribute("submitForm", new User());
		
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			map.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			map.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		return "client/dang-ky";
	}
	
	@PostMapping("/dang-ky/save")
	public String save(Model map, @Validated @ModelAttribute("submitForm") User users,
			BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "client/dang-ky";
		}
		try {
			userService.createUser(users);
			session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
		} catch (Exception e) {
			// TODO: handle exception
			session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
		}
		return "redirect:/dang-ky";
	}
	

	 
	 
}
