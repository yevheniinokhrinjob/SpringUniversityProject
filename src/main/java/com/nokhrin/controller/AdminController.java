package com.nokhrin.controller;

import com.nokhrin.domain.AppUser;
import com.nokhrin.domain.Doctor;
import com.nokhrin.domain.Visit;
import com.nokhrin.service.DoctorService;
import com.nokhrin.service.UserService;
import com.nokhrin.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {

    private VisitService visitService;
    private UserService userService;
    private DoctorService doctorService;
    @Autowired
    public AdminController(VisitService visitService, UserService appUserService, DoctorService doctorService) {
        this.visitService=visitService;
        this.userService = appUserService;
        this.doctorService=doctorService;
    }

    @RequestMapping(value = "/acceptPayment")
    public String getPaymentList(Model model)
    {
        List<Visit> unpaidVisits = visitService.getUnpaidVisits(false);
            for (Visit v:  unpaidVisits) {
                v.getDate().setTime(v.getDate().getTime() + v.getTime().getTime());
            }
            model.addAttribute("unpaidList", unpaidVisits);
            return "acceptPayment";
    }

    @RequestMapping(value = "/paid/{unpaidId}")
    public String acceptPayment(@PathVariable("unpaidId") Long unpaidId) {
        Visit visit =visitService.getVisit(unpaidId);
        visit.setPaid(true);
        visitService.editVisit(visit);
        return "redirect:/acceptPayment.html";
    }

    @RequestMapping(value = "/removeDoctor")
    public String removeDoctor(Model model) { model.addAttribute("listDoctors", doctorService.listDoctor());


        return "removeDoctor";
    }

    @RequestMapping(value = "/addDoctor")
    public String addDoctor(Model model, HttpServletRequest request) {
        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId" , -1);
        if (appUserId > 0){
            AppUser appUser = userService.getAppUser(appUserId);
            Doctor doctor = new Doctor();
            doctor.setAppUser(appUser);
            doctor.setPosition("");
            doctor.setSalary(0);
            model.addAttribute("isSelected",true);
            model.addAttribute("doctor", doctor);
        }else {
            model.addAttribute("isSelected",false);
            model.addAttribute("doctor", new Doctor());
        }

        List<AppUser> users = new ArrayList<AppUser>();
        for (AppUser user : userService.listAppUser()) {
            boolean isDoctor=false;
            for (Doctor doctor: doctorService.listDoctor()
            ) {

                if(doctor.getAppUser().getId()==user.getId()){
                    isDoctor=true;
                }
            }
            if(!isDoctor){
                users.add(user);
            }
        }

        model.addAttribute("listUsers", users);
        return "addDoctor";
    }

    @RequestMapping(value = "/addNewDoctor",method = RequestMethod.POST)
    public String addNewDoctor(@ModelAttribute("doctor") Doctor doctor){
        AppUser appUser = userService.getAppUser(doctor.getAppUser().getId());
        //      System.out.println(appUser.getlName());
        appUser.setDoctor(doctor);
        userService.editAppUser(appUser);
       // doctor.setAppUser(appUser);
      //  doctorService.addDoctor(doctor);
        userService.addRoleToAppUser(appUser,"ROLE_DOCTOR");
        //    System.out.println(doctor.getAppUser().getId());
        return "redirect:/myprofile.html";
    }

    @RequestMapping(value = "/removeD/{doctorId}")
    public String removeDoctor(@PathVariable("doctorId") Long doctorId) {
        AppUser user =userService.getAppUser(doctorService.getDoctor(doctorId).getAppUser().getId());
        //  UserRoles roles = userRoleService.getAppUserRole("ROLE_DOCTOR");
        userService.removeAppUserRole(user,"ROLE_DOCTOR" );
        user.setDoctor(null);
        userService.editAppUser(user);
     //   doctorService.removeDoctor(doctorId);
      //  System.out.println("doc id " + doctorId);
        return "redirect:/myprofile.html";
    }

}

