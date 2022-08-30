package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.app.entity.Orders;
import com.app.entity.Product;
import com.app.entity.User;
import com.app.exception.ResourceNotFoundException;
import com.app.model.PagingSearchFilterOrder;
import com.app.service.OrderService;
import com.app.service.ProductService;
import com.app.service.UserService;
import com.app.utils.ApprovedStep;
import com.app.utils.Constant;
import com.app.validator.OrderValidator;

@Controller
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderValidator orderValidator;
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	 
 
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
 
	@InitBinder
	public void dataBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == Orders.class) {
			binder.setValidator(orderValidator);
		}
	}
	 
	@RequestMapping("/list/{page}")
	public String showProduct(Model model, @ModelAttribute("searchForm") PagingSearchFilterOrder searchForm, HttpSession session, @PathVariable("page") int page) {
		searchForm.setPage(page);
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
		
		return "orders/orders-list";
	}
// 
//	@GetMapping("/delete/{id}")
//	public String delete(ModelMap map,@PathVariable("id")long id, HttpSession session) {
//		Orders orders = orderService.getById(id);
//		if(orders == null) {
//			throw new ResourceNotFoundException("orders not found with id :" + id);
//		}
//		try {
//			orderService.deleteOrder(orders);
//			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
//		} catch (Exception e) {
//			// TODO: handle exception
//			log.warn("delete faild  :"+ e.getMessage());
//			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
//		}
//		return "redirect:/orders/list/1";
//	}
	
	@GetMapping("/cancel/{id}")
	public String cancel(ModelMap map,@PathVariable("id")long id, HttpSession session) {
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
			log.warn("delete faild  :"+ e.getMessage());
			session.setAttribute(Constant.MSG_ERROR, "Huỷ thất bại");
		}
		return "redirect:/orders/list/1";
	}
	
	@GetMapping("/complete/{id}")
	public String complete(ModelMap map,@PathVariable("id")long id, HttpSession session) {
		Orders orders = orderService.getById(id);
		if(orders == null) {
			throw new ResourceNotFoundException("orders not found with id :" + id);
		}
		try {
			orders.setApprovedStep(ApprovedStep.COMPLETE.getValue());
			orderService.updateOrder(orders);
			session.setAttribute(Constant.MSG_SUCCESS, "Duyệt thành công");
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("delete faild  :"+ e.getMessage());
			session.setAttribute(Constant.MSG_ERROR, "Duyệt thất bại");
		}
		return "redirect:/orders/list/1";
	}
	
	@PostMapping("/save")
	public String save(Model map, @Validated @ModelAttribute("submitForm") Orders orderss,
			BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			Product product = productService.getById(orderss.getProducts().getId());
			orderss.setProducts(product);
			map.addAttribute("submitForm", orderss);
			return "client/thanh-toan";
		}
		try {
			Product product = productService.getById(orderss.getProducts().getId());
			orderss.setApprovedStep(ApprovedStep.IN_PROGRESS.getValue());
			orderss.setPrice(product.getPrice());
			orderss.setDate(new Date());
			String username = (String) session.getAttribute(Constant.USER_INFO);
			User user = userService.getByName(username);
			orderss.setUser(user);
			orderService.createOrder(orderss);
			session.setAttribute(Constant.MSG_SUCCESS, "Đặt thành công");
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("add faild  :"+ e.getMessage());
			session.setAttribute(Constant.MSG_ERROR, "Đặt thất bại");
		}
		return "redirect:/trang-chu";
	}
 
	
 
}
