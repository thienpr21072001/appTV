package com.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.entity.Orders;

@Component
public class OrderValidator implements Validator{

 
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == Orders.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "name", "error.required");
		ValidationUtils.rejectIfEmpty(errors, "phone", "error.required");
 
	}
   
}
