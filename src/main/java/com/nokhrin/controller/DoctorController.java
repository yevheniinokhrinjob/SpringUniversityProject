package com.nokhrin.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Secured({"ROLE_ADMIN","ROLE_DOCTOR"})
public class DoctorController {

    private VisitService visitService;
    private DoctorService doctorService;
    @Autowired
    public DoctorController(VisitService visitService, DoctorService doctorService){
        this.visitService=visitService;
        this.doctorService=doctorService;
    }
    @RequestMapping(value = "/visitList")
    public String getListOfVisits(Model model, HttpServletRequest request){
        Doctor doctor= new Doctor();
        for (Doctor doc: doctorService.listDoctor()) {
       //     System.out.println(doc.getId() + " " + doc.getAppUser().getLogin());
            if(doc.getAppUser().getLogin().equals(request.getUserPrincipal().getName())){
                doctor=doc;
            }
        }
        List<Visit> visitList = visitService.getVisitsListForDoc(doctor);
        for (Visit v:  visitList) {
            v.getDate().setTime(v.getDate().getTime() + v.getTime().getTime());
        }
        model.addAttribute("visitList", visitList);
        return  "visitList";
    }

    @RequestMapping(value = "/completeVisit")
    public String completeVisit(Model model, HttpServletRequest request) {
        int visitId = ServletRequestUtils.getIntParameter(request, "visitId", -1);
        Visit visit = visitService.getVisit(visitId);
        visit.setActual(false);
        visitService.editVisit(visit);
        return "redirect:/myprofile.html";
    }
}
