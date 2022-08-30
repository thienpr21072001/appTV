package com.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.entity.Product;
import com.app.service.ProductService;
import com.app.utils.Constant;

@Component
public class ProductValidator implements Validator{

	@Autowired
	ProductService productService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == Product.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "name", "error.required");
		ValidationUtils.rejectIfEmpty(errors, "price", "error.required");
		ValidationUtils.rejectIfEmpty(errors, "numberDay", "error.required");
		ValidationUtils.rejectIfEmpty(errors, "dateStart", "error.required");
		 
		Product products = (Product) target;
		
		if(products != null) {
			if(products.getName() != null) {
				Product oldProduct =  productService.getByName(products.getName());
				if(oldProduct != null) {
					if(oldProduct.getId() != products.getId()) {
						errors.rejectValue("name", "error.exists");
					}			
				}
//				if(!products.getName().matches(Constant.REGEX_NAME_PRODUCT)) {
//					errors.rejectValue("name", "error.format");
//				}
			}
			if(products.getNumberDay() <=0) {
				errors.rejectValue("numberDay", "error.format");
			}
			
//			if(products.getCategory() != null) {
//				if(products.getCategory().getId() ==  0) {
//					errors.rejectValue("category", "error.required");
//				}
//			} 
 
//			if(products.getPrice() != null) {
//				if(!products.getPrice().toString().matches(Constant.REGEX_PRICE)) {
//					errors.rejectValue("price", "error.format");
//				 
//				}
//			}
		
		
	}
  }
}
