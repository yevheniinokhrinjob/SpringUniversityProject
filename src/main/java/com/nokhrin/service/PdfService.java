package com.nokhrin.service;

import com.nokhrin.domain.Visit;


import javax.servlet.http.HttpServletResponse;

public interface PdfService {
    public void generatePdf(Visit visit, HttpServletResponse response);
}
