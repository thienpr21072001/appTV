package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.entity.Product;
import com.app.entity.User;
import com.app.model.LoginRequest;
import com.app.repository.ProductRepository;
import com.app.service.UserService;
import com.app.utils.Constant;
import com.app.validator.LoginValidator;
 
@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	LoginValidator loginValidator;
	 
	@InitBinder
	public void dataBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == LoginRequest.class) {
			binder.setValidator(loginValidator);
		}
	}
	
	@GetMapping(value = {"","/index","/home"})
	public String home(HttpSession session) {
		return "index";
	}
	
	@GetMapping(value = {"/login"})
	public String login(ModelMap map) {
		map.addAttribute("submitForm", new User());
		return "login/login";
	}
	
	
	@PostMapping(value = {"/processLogin"})
	public String processLogin(ModelMap map, @Validated @ModelAttribute("submitForm") LoginRequest loginRequest, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "login/login";
		}
		User user = userService.getByName(loginRequest.getUsername());
		if(user.getRole() == 2) {
			return "redirect:/dang-nhap";
		}
		session.setAttribute(Constant.USER_INFO, user.getUsername());
		
		return "redirect:/index";
	}
	
	@GetMapping(value = {"/logout"})
	public String logout(HttpSession session) {
		session.removeAttribute(Constant.USER_INFO);
		return "redirect:/login";
	}
	
	//client
	
	@GetMapping(value = {"/trang-chu"})
	public String trangChu(HttpSession session, ModelMap map) {
		List<Product> productList = productRepo.findNew6Product();
		map.addAttribute("products", productList);
		return "client/index";
	}
	
	@GetMapping(value = {"/gioi-thieu"})
	public String gioiThieu(HttpSession session, ModelMap map) {
		 
		return "client/introduce";
	}
	
	@GetMapping(value = {"/dang-nhap"})
	public String dangNhap(HttpSession session, ModelMap map) {
		map.addAttribute("submitForm", new User());
		return "client/dang-nhap";
	}
	
	@PostMapping(value = {"/xu-ly"})
	public String xuLy(ModelMap map, @Validated @ModelAttribute("submitForm") LoginRequest loginRequest, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "client/dang-nhap";
		}
		 
		User user = userService.getByName(loginRequest.getUsername());
		if(user.getRole() == 1) {
			return "redirect:/login";
		}
		session.setAttribute(Constant.USER_INFO, user.getUsername());
		return "client/index";
	}
}
