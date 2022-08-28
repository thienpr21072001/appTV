package com.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.entity.User;
import com.app.model.LoginRequest;
import com.app.service.UserService;

@Component
public class LoginValidator implements Validator{

	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == LoginRequest.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "username", "error.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "error.required");
		
		LoginRequest instance = (LoginRequest) target;
	 
		if(instance != null) {
			if(instance.getUsername() != null) {
				User oldInstance =  userService.getByName(instance.getUsername());
				if(oldInstance != null) {
					if(!oldInstance.getPassword().equals(instance.getPassword())) {
						errors.rejectValue("password", "error.login.faild");
					}			
				}
//				if(!products.getName().matches(Constant.REGEX_NAME_PRODUCT)) {
//					errors.rejectValue("name", "error.format");
//				}
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
