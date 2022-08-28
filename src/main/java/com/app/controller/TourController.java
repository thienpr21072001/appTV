/**
 * 
 */
package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.Product;
import com.app.model.PagingSearchFilterProduct;
import com.app.service.ProductService;
import com.app.utils.Constant;

@Controller
@RequestMapping("/tour")
public class TourController {
	
	@Autowired
	ProductService productService;
	

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
}
