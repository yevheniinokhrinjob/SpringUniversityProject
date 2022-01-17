package com.nokhrin.controller;

import com.nokhrin.domain.AppUser;
import com.nokhrin.domain.VerificationToken;
import com.nokhrin.domain.Visit;
import com.nokhrin.service.*;
import com.nokhrin.validator.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Random;


@Controller
@EnableScheduling
public class MainController {
    private AppUserValidator appUserValidator = new AppUserValidator();
    private UserService userService;
    private DoctorService doctorService;
    private UserRoleService userRoleService;
    private VerificationTokenService tokenService;
    private Sender mailSender= new Sender("storojuko@gmail.com", "Proj_Zesp2020");
    private VisitService visitService;

@Autowired
public MainController(UserService appUserService,  VisitService visitService,DoctorService doctorService, UserRoleService userRoleService, VerificationTokenService tokenService){
    this.userService = appUserService;
    this.doctorService=doctorService;
    this.userRoleService=userRoleService;
    this.tokenService=tokenService;
    this.visitService=visitService;

}

    @RequestMapping(value = "")
    public String showMainPaige() {

        return "main";
    }
    @RequestMapping(value = "/contacts")
    public String showContacts() {
        return "contacts";
    }

    @RequestMapping(value = "/prices")
    public String showPrices() {
        return "prices";
    }
    @RequestMapping(value = "/register")
    public String showAppUsers(Model model, HttpServletRequest request) {

        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId" , -1);

        if (appUserId > 0)
            model.addAttribute("appUser", userService.getAppUser(appUserId));
        else
            model.addAttribute("appUser", new AppUser());

        model.addAttribute("appUserList", userService.listAppUser());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result) {

        appUser.setLogin(appUser.getEmail());
        appUserValidator.setUserService(userService);
        appUserValidator.validate(appUser, result);

        if (result.getErrorCount() == 0) {
            userService.addAppUser(appUser);
            VerificationToken token = new VerificationToken();
            token.setToken(generateString(15));
            token.setAppUser(appUser);
            tokenService.addVerificationToken(token);
            mailSender.send("Account verification","http://localhost:8080/varification_token/"+token.getToken(),"storojuko@gmail.com","nohrin.j@gmail.com");
            return "redirect:/myprofile.html";
        }
        else
            return "register";
    }
    @RequestMapping(value="/varification_token/{token}", method=RequestMethod.GET)
    public String verificationToken(@PathVariable("token") String token){
        VerificationToken token1 = tokenService.getTockenByTockenCode(token);
        token1.getAppUser().setEnabled(true);
        userService.editAppUser(token1.getAppUser());
        tokenService.removeVerificationToken(token1);
        return "redirect:/myprofile.html";
    }
    @RequestMapping(value = "/doctors")
    public String showDoctorsList(Model model) {
    model.addAttribute("listDoctors", doctorService.listDoctor());
    return "doctors";
    }
   /* @Scheduled(fixedDelay = 20000)
    public void scheduleFixedDelayTask() {
        for (Visit v: visitService.getUnpaidVisits(false)
        ) {
            if(v.isActual()==false){

           //     mailSender.send("Payment reminder",getMessageString(v),"storojuko@gmail.com","nohrin.j@gmail.com");
                System.out.println(getMessageString(v));
            }
        }

    }*/
    private String generateString(int length)
    {
        String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOASDFGHJKLZXCVBNM";
        Random rnd = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        return new String(text);
    }
    private String getMessageString(Visit visit){
        String s = "Hello, " + visit.getClient().getfName() + " " + visit.getClient().getlName() + ", you have unpaid visit for " + visit.getDate().toString().substring(0,10) +". Please pay " + visit.getPrice() + ". Your can get a bill in our site: http://localhost:8080" ;
        return  s;
    }
}
