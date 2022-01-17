package com.nokhrin.controller;


import com.nokhrin.dao.UserRepository;
import com.nokhrin.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appUserRest")
public class UserRestController {
    private UserRepository userRepository;
    @Autowired
    public UserRestController(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = "application/xml")
    public AppUser getAppUserInJSON(@PathVariable String login){
        return  userRepository.findByLogin(login);
    }
}
