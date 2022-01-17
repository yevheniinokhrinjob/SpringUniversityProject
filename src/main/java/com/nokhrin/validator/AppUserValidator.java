package com.nokhrin.validator;

import com.nokhrin.domain.AppUser;
import com.nokhrin.service.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppUserValidator implements Validator {

	EmailValidator emailValidator = EmailValidator.getInstance();
	UserService userService;
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	@Override
	public boolean supports(Class clazz) {
		return AppUser.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors errors) {

	//	if(userService.findByLogin())
		AppUser appUser=(AppUser)arg0;
		if (StringUtils.hasText(appUser.getEmail()) && emailValidator.isValid(((AppUser)arg0).getEmail()) == false) {
			errors.rejectValue("email", "error.email.invalid");
		}
		if(userService.findByLogin(appUser.getLogin())!=null){
			errors.rejectValue("email", "error.email.exists");
		}
		Pattern p = Pattern.compile("\\+[1-9]\\d{10}");
		Matcher matcher = p.matcher(appUser.getPhone());
		if (!matcher.matches()) {
			errors.rejectValue("phone", "error.phone.invalid");
		}
		Pattern p1 = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");

	}

}

