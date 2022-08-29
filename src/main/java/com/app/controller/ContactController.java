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

import com.app.entity.Contact;
import com.app.exception.ResourceNotFoundException;
import com.app.model.PagingSearchFilterContact;
import com.app.service.ContactService;
import com.app.utils.Constant;
import com.app.validator.ContactValidator;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	@Autowired
	ContactService contactService;
	@Autowired
	ContactValidator contactValidator;
	 
 
	private static final Logger log = LoggerFactory.getLogger(ContactController.class);
 
	@InitBinder
	public void dataBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == Contact.class) {
			binder.setValidator(contactValidator);
		}
	}
	 
	@RequestMapping("/list/{page}")
	public String showProduct(Model model, @ModelAttribute("searchForm") PagingSearchFilterContact searchForm, HttpSession session, @PathVariable("page") int page) {
		searchForm.setPage(page);
		Page<Contact> pageProduct = contactService.getAll(searchForm);
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
		
		return "contact/contact-list";
	}
 
	@PostMapping(value = {"/save"})
	public String saveLienHe(ModelMap  map, @Validated @ModelAttribute("submitForm") Contact contact, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "client/lien-he";
		}
		try {
			contactService.createContact(contact);
			//session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("update faild  :"+ e.getMessage());
			//session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
		}
		return "redirect:/lien-he";
	}
 
}
