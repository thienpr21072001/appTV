/**
 * 
 */
package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.entity.Product;
import com.app.model.PagingSearchFilterProduct;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;
import com.app.utils.Constant;

@Controller
@RequestMapping("/tour")
public class TourController {
	
	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepo;
	
	@RequestMapping("/list/{page}")
	public String showProduct(Model model, @ModelAttribute("searchForm") PagingSearchFilterProduct searchForm, HttpSession session, 
			@PathVariable("page") int page, @RequestParam("provinceId") Long provinceId) {
		searchForm.setPage(page);
		searchForm.setProvinceId(provinceId);
		searchForm.setPageSize(9);
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
		
		List<Product> productList = productRepo.findNew6Product();
		model.addAttribute("products", productList);
	 
		
		return "client/tour";
	}
}
