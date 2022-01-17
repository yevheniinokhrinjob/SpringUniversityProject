package com.nokhrin.controller;

import com.nokhrin.domain.Visit;
import com.nokhrin.service.PdfService;
import com.nokhrin.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PdfController {
    private PdfService pdfService;
    private VisitService visitService;

    @Autowired
    public PdfController(PdfService pdfService, VisitService visitService){
        this.pdfService=pdfService;
        this.visitService=visitService;
    }
    @RequestMapping(value = "/generatePdf-{visitId}", method = RequestMethod.GET)
    public void generatePdf(@PathVariable Integer visitId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if(visitService.getVisit(visitId)==null||request.getUserPrincipal()==null ||!visitService.getVisit(visitId).getClient().getLogin().equals( request.getUserPrincipal().getName())){
//System.out.println("denied");
            response.sendRedirect("/accessDenied");
        }
     else {
            pdfService.generatePdf(visitService.getVisit(visitId), response);
        }
    }
}
