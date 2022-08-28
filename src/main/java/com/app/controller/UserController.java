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

import com.app.entity.User;
import com.app.exception.ResourceNotFoundException;
import com.app.model.PagingSearchFilterUser;
import com.app.service.UserService;
import com.app.utils.Constant;
import com.app.validator.UserValidator;

@Controller
@RequestMapping("/users")
public class UserController {
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
	 
	@RequestMapping("/list/{page}")
	public String showProduct(Model model, @ModelAttribute("searchForm") PagingSearchFilterUser searchForm, HttpSession session, @PathVariable("page") int page) {
		searchForm.setPage(page);
		Page<User> pageProduct = userService.getAll(searchForm);
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
		
		model.addAttribute(Constant.USER_INFO, session.getAttribute(Constant.USER_INFO));
		
		return "user/user-list";
	}
 
	@GetMapping("/add")
	public String news(Model map) {
		map.addAttribute("submitForm", new User());
		map.addAttribute("viewOnly", false);
		map.addAttribute("title", "Add");
		return "user/user-action";
	}
	@GetMapping("/edit/{id}")
	public String edit(Model map,@PathVariable("id")long id) {
		User user = userService.getById(id);
		if(user == null) {
			throw new ResourceNotFoundException("user not found with id :" + id);
		}
		map.addAttribute("submitForm", user);
		map.addAttribute("viewOnly", false);
		map.addAttribute("title", "Edit");
		return "user/user-action";
	}
	
	@GetMapping("/views/{id}")
	public String view(Model map,@PathVariable("id") long id) {
		User user = userService.getById(id);
		if(user == null) {
			throw new ResourceNotFoundException("user not found with id :" + id);
		}
		map.addAttribute("submitForm", user);
		map.addAttribute("title", "View");
		map.addAttribute("viewOnly", true);
		return "user/user-action";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(ModelMap map,@PathVariable("id")long id, HttpSession session) {
		User user = userService.getById(id);
		if(user == null) {
			throw new ResourceNotFoundException("user not found with id :" + id);
		}
		try {
			userService.deleteUser(user);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("delete faild  :"+ e.getMessage());
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
		return "redirect:/users/list/1";
	}
	@PostMapping("/save")
	public String save(Model map, @Validated @ModelAttribute("submitForm") User users,
			BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "user/user-action";
		}
		if(users.getId() != 0) {
			try {
				userService.updateUser(users);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO: handle exception
				log.warn("update faild  :"+ e.getMessage());
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				userService.createUser(users);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO: handle exception
				log.warn("add faild  :"+ e.getMessage());
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		return "redirect:/users/list/1";
	}
 
}
