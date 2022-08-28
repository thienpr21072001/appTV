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

import com.app.entity.Product;
import com.app.exception.ResourceNotFoundException;
import com.app.model.PagingSearchFilterProduct;
import com.app.service.ProductService;
import com.app.utils.Constant;
import com.app.validator.ProductValidator;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductValidator productValidator;
	 
 
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
 
	@InitBinder
	public void dataBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == Product.class) {
			binder.setValidator(productValidator);
		}
	}
	 
	@RequestMapping("/list/{page}")
	public String showProduct(Model model, @ModelAttribute("searchForm") PagingSearchFilterProduct searchForm, HttpSession session, @PathVariable("page") int page) {
		searchForm.setPage(page);
		Page<Product> pageProduct = productService.getAll(searchForm);
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
		
		return "product/product-list";
	}
 
	@GetMapping("/add")
	public String news(Model map) {
		map.addAttribute("submitForm", new Product());
		map.addAttribute("viewOnly", false);
		map.addAttribute("title", "Add");
		return "product/product-action";
	}
	@GetMapping("/edit/{id}")
	public String edit(Model map,@PathVariable("id")long id) {
		Product product = productService.getById(id);
		if(product == null) {
			throw new ResourceNotFoundException("product not found with id :" + id);
		}
		map.addAttribute("submitForm", product);
		map.addAttribute("viewOnly", false);
		map.addAttribute("title", "Edit");
		return "product/product-action";
	}
	
	@GetMapping("/views/{id}")
	public String view(Model map,@PathVariable("id") long id) {
		Product product = productService.getById(id);
		if(product == null) {
			throw new ResourceNotFoundException("product not found with id :" + id);
		}
		map.addAttribute("submitForm", product);
		map.addAttribute("title", "View");
		map.addAttribute("viewOnly", true);
		return "product/product-action";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(ModelMap map,@PathVariable("id")long id, HttpSession session) {
		Product product = productService.getById(id);
		if(product == null) {
			throw new ResourceNotFoundException("product not found with id :" + id);
		}
		try {
			productService.deleteProducts(product);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO: handle exception
			log.warn("delete faild  :"+ e.getMessage());
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
		return "redirect:/products/list/1";
	}
	@PostMapping("/save")
	public String save(Model map, @Validated @ModelAttribute("submitForm") Product products,
			BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "product/product-action";
		}
		if(products.getId() != 0) {
			try {
				productService.updateProduct(products);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO: handle exception
				log.warn("update faild  :"+ e.getMessage());
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				productService.createProduct(products);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO: handle exception
				log.warn("add faild  :"+ e.getMessage());
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		return "redirect:/products/list/1";
	}
 
}
