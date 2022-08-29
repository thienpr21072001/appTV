package com.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.entity.Contact;
import com.app.service.ContactService;

@Component
public class ContactValidator implements Validator{

	@Autowired
	ContactService productService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == Contact.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "name", "error.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.required");
		ValidationUtils.rejectIfEmpty(errors, "content", "error.required");
	}
   
}
