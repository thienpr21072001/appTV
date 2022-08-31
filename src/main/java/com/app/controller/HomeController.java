package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.Contact;
import com.app.entity.Orders;
import com.app.entity.Product;
import com.app.entity.User;
import com.app.exception.ResourceNotFoundException;
import com.app.model.LoginRequest;
import com.app.model.PagingSearchFilterOrder;
import com.app.repository.ProductRepository;
import com.app.service.OrderService;
import com.app.service.ProductService;
import com.app.service.UserService;
import com.app.utils.ApprovedStep;
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
	
	@Autowired
	ProductService productService;
	 
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
		if(user != null && user.getRole() == 2) {
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
		
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			map.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			map.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		
		
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
		if(user != null && user.getRole() == 1) {
			return "redirect:/login";
		}
		session.setAttribute(Constant.USER_INFO, user.getUsername());
		 
		return "redirect:/trang-chu";
	}
	
	@GetMapping(value = {"/thoat"})
	public String thoat(HttpSession session) {
		session.removeAttribute(Constant.USER_INFO);
		 
		return "redirect:/trang-chu";
	}
	
	@GetMapping(value = {"/lien-he"})
	public String lienHe(HttpSession session, ModelMap map) {
		 
		map.addAttribute("submitForm", new Contact());
		return "client/lien-he";
	}
	
	@Autowired
	OrderService orderService;
	 
	
	@GetMapping("/thanh-toan/{id}")
	public String news(Model map, HttpSession session, @PathVariable("id") Long id) {
 
		String username = (String) session.getAttribute(Constant.USER_INFO);
		User user = userService.getByName(username);
		if(user == null || user.getRole() == 1) {
			return "redirect:/dang-nhap";
		}
		Product product = productService.getById(id);
		Orders orders = new Orders();
		orders.setProducts(product);
		map.addAttribute("submitForm", orders);
		map.addAttribute("viewOnly", false);
		 
		map.addAttribute("product", product);
		
		return "client/thanh-toan";
	}
	
	@RequestMapping("/lich-tour/{page}")
	public String lichTour(Model model,  HttpSession session, @PathVariable("page") int page) {
		String username = (String) session.getAttribute(Constant.USER_INFO);
		User user = userService.getByName(username);
		PagingSearchFilterOrder searchForm = new PagingSearchFilterOrder();
		searchForm.setPage(page);
		searchForm.setUserId(user.getId());
		
		Page<Orders> pageProduct = orderService.getAll(searchForm);
		model.addAttribute("searchForm", searchForm);
		model.addAttribute("pageProduct",pageProduct);
 
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		
		return "client/lich-tour-i";
	}
	
	@GetMapping("/lich-tour/cancel/{id}")
	public String cancel(ModelMap map,@PathVariable("id") long id, HttpSession session) {
		Orders orders = orderService.getById(id);
		if(orders == null) {
			throw new ResourceNotFoundException("orders not found with id :" + id);
		}
		try {
			orders.setApprovedStep(ApprovedStep.CANCEL.getValue());
			orderService.updateOrder(orders);
			session.setAttribute(Constant.MSG_SUCCESS, "Huỷ thành công");
		} catch (Exception e) {
			// TODO: handle exception
			 
			session.setAttribute(Constant.MSG_ERROR, "Huỷ thất bại");
		}
		return "redirect:/lich-tour/1";
	}
	
	
 
	
 
 
}
