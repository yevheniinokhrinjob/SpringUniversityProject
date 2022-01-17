package com.nokhrin.controller;

import com.nokhrin.domain.*;
import com.nokhrin.service.*;
import com.nokhrin.validator.AppUserValidator;

import com.nokhrin.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Console;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller

@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_DOCTOR"})
public class UserController
{

    private UserService userService;
    private DoctorService doctorService;
    private UserRoleService userRoleService;

    private PasswordValidator passwordValidator = new PasswordValidator();
    private VisitService visitService;
    private VerificationTokenService tokenService;
    private Sender mailSender= new Sender("storojuko@gmail.com", "bpmnproject2019");
    private ReCaptchaService reCaptchaService;
    @Autowired
    public UserController(UserService appUserService, DoctorService doctorService, UserRoleService userRoleService, VisitService visitService, VerificationTokenService tokenService, ReCaptchaService reCaptchaService) {
        this.userService = appUserService;
        this.doctorService=doctorService;
        this.userRoleService=userRoleService;
        this.visitService=visitService;
        this.tokenService=tokenService;
        this.reCaptchaService=reCaptchaService;
    }

    @RequestMapping(value = "/myprofile")
    public String showMyProfile(Model model,  HttpServletRequest request) {
        if(request.getUserPrincipal()!=null) {
            model.addAttribute("hasActualVisit", hasActualVisit(request.getUserPrincipal().getName()));
            model.addAttribute("hasUnpaidVisit", hasUnpaidVisit(request.getUserPrincipal().getName()));
        }
        return "myprofile";
    }
   /* @RequestMapping(value = "/send")
        public String sendM(){
            mailSender.send("1","321321","storojuko@gmail.com","nohrin.j@gmail.com");
        return "redirect:myprofile.html";
        }*/




    @RequestMapping(value = "/editAppUser")
    public String showEditionAppUsers(Model model, HttpServletRequest request) {
String appUserLogin=request.getUserPrincipal().getName();
    model.addAttribute("appUser", userService.findByLogin(appUserLogin));


        model.addAttribute("appUserList", userService.listAppUser());

        return "editAppUser";
    }
    @RequestMapping(value = "/editAppUser", method = RequestMethod.POST)
    public String editAppUser(@ModelAttribute("appUser") AppUser appUser,BindingResult result,HttpServletRequest request) {
     if(reCaptchaService.verify(request.getParameter("g-recaptcha-response"))) {
            AppUser user = userService.getAppUser(appUser.getId());
            user.getAddress().setCity(appUser.getAddress().getCity());
            user.getAddress().setStreet(appUser.getAddress().getStreet());
            user.getAddress().setHouseNumber(appUser.getAddress().getHouseNumber());
            user.setPhone(appUser.getPhone());
            user.setEmail(appUser.getEmail());
            user.setLogin(appUser.getEmail());
            userService.editAppUser(user);

            return "redirect:myprofile.html";
        }
     else {
          return  "editAppUser";
     }
    }
    @RequestMapping(value = "/editPassword")
    public String showPasswordEdition(Model model){
        model.addAttribute("passModel", new EditPassword());
        return "editPassword";
    }
    @RequestMapping(value = "/editPassword", method = RequestMethod.POST)
    public  String editPassword(@ModelAttribute("passModel") EditPassword editPassword, HttpServletRequest request,BindingResult result){
            editPassword.setAppUser(userService.findByLogin(request.getUserPrincipal().getName()));
            passwordValidator.validate(editPassword,result);
            if(result.getErrorCount()==0){
               // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                AppUser appUser = userService.getAppUser(editPassword.getAppUser().getId());
            //    appUser.setPassword(passwordEncoder.encode(editPassword.getNewPass()));
                appUser.setPassword(editPassword.getNewPass());
                userService.editAppUserPassword(appUser);
                return "redirect:/myprofile.html";
            }
            return "editPassword";
    }
    @RequestMapping(value = "/createVisit")
    public  String getAvaliableDate(Model model, HttpServletRequest request) throws ParseException {
        if(hasUnpaidVisit(request.getUserPrincipal().getName())){
           return  "redirect:payForVisit.html";
        }else {
            int doctorId = ServletRequestUtils.getIntParameter(request, "doctorId", -1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Visit visit = new Visit();
            Date d = new Date();
            String stringDate = dateFormat.format(d);
       //     System.out.println(stringDate);
            visit.setDate(d);
            visit.setPrice(1000);
            if (doctorId > 0) {
                visit.setDoctor(doctorService.getDoctor(doctorId));
                model.addAttribute("isSelected",true);
                model.addAttribute("visit", visit);
            } else {
                model.addAttribute("isSelected",false);
                model.addAttribute("visit", visit);
            }

            model.addAttribute("stringDate", stringDate);
            model.addAttribute("listDoctors", doctorService.listDoctor());
            return "createVisit";
        }
    }
    @RequestMapping(value = "/createVisit", method = RequestMethod.POST)
    public String setAvaliableDate(@ModelAttribute("visit") Visit visit, HttpServletRequest request) {
        request.getSession().setAttribute("thisVisit", visit);
        request.getSession().setAttribute("dateSet", true);
        return "redirect:selectTime.html";
    }
    @RequestMapping(value = "/selectTime")
    public String getAvaliableTime(Model model,HttpServletRequest request) {
        Visit visit = (Visit) request.getSession().getAttribute("thisVisit");
        if(visit==null){
            return "redirect:/createVisit.html";
        }
        visit.setDoctor(doctorService.getDoctor(visit.getDoctor().getId()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<Time> avaliableTime= visitService.getAvailableHours(visit);
        String stringDate =  dateFormat.format(visit.getDate());
        model.addAttribute("timeList",avaliableTime);
        model.addAttribute("stringDate", stringDate);
        model.addAttribute("visit",visit);

        return "selectTime";
    }
    @RequestMapping(value = "/createNewVisit",method = RequestMethod.POST)
    public String addNewVisit(@ModelAttribute("visit") Visit visit,HttpServletRequest request){
     //   System.out.println(visit.getDate());
        String appUserLogin=request.getUserPrincipal().getName();
        visit.setClient(userService.findByLogin(appUserLogin));
        visit.setDoctor(doctorService.getDoctor(visit.getDoctor().getId()));
        visit.setPrice(1000);
        visit.setActual(true);
        visit.setPaid(false);

        visitService.addVisit(visit);
        return "redirect:/myprofile.html";
    }

    @RequestMapping(value = "/payForVisit")
    public String showYourVisitPaymentInfo(Model model,HttpServletRequest request){
        String appUserLogin=request.getUserPrincipal().getName();
        Visit thisVisit= getUpaidVisit(appUserLogin);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate =  dateFormat.format(thisVisit.getDate());
        model.addAttribute("stringDate", stringDate);
        model.addAttribute("visit", thisVisit);
        return "payForVisit";
    }

    @RequestMapping(value = "/cancelVisit")
    public String cancelVisit(Model model, HttpServletRequest request){
        if(!hasActualVisit(request.getUserPrincipal().getName())){
            return "redirect:/payForVisit.html";
        }else {
            Visit visit = visitService.getActiveVisitByLogin(request.getUserPrincipal().getName());
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String stringDate =  dateFormat.format(visit.getDate());
            model.addAttribute("stringDate", stringDate);
            model.addAttribute("visit", visit);
            return "cancelVisit";
        }
    }
    @RequestMapping(value = "/cancelVisit",method = RequestMethod.POST)
    public String acceptVisitCanceling(@ModelAttribute("visit") Visit visit){
        visitService.removeVisit(visit.getId());
        return "redirect:/myprofile.html";
    }



  private Visit getUpaidVisit(String userLogin){
      AppUser appUser = userService.findByLogin(userLogin);
      return  visitService.getUpaidVisit(appUser);
  }
    private  boolean hasUnpaidVisit(String userLogin){
        AppUser appUser = userService.findByLogin(userLogin);
        return  visitService.hasUnpaidVisit(appUser);
    }

    private  boolean hasActualVisit(String userLogin){

        AppUser appUser = userService.findByLogin(userLogin);
        return  visitService.hasActualVisit(appUser);
    }


}
