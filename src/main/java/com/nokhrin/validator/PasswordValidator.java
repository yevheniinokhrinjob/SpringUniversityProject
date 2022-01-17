package com.nokhrin.validator;

import com.nokhrin.domain.AppUser;
import com.nokhrin.domain.EditPassword;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return AppUser.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object arg0, Errors errors) {
        EditPassword password = (EditPassword)arg0;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
     //   String oldPassword =passwordEncoder.encode(password.getOldPass());
        System.out.println(password.getOldPass());
        if(!passwordEncoder.matches(password.getOldPass(),password.getAppUser().getPassword())){
        //    System.out.println(oldPassword+"   "+password.getAppUser().getPassword());
            errors.rejectValue("oldPass", "error.oldPassword.invalid");
        }
    }
}
