package com.nokhrin.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nokhrin.domain.Visit;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfServiceImpl implements  PdfService{
    public void generatePdf(Visit visit, HttpServletResponse response){
        try{
            OutputStream o = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=visit" + visit.getId() + ".pdf");
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, o);
            pdf.open();
            pdf.add(new Paragraph("Pay for visit №" + visit.getId()));
            pdf.add(new Paragraph(Chunk.NEWLINE));
            PdfPTable table = new PdfPTable(2);

            table.addCell("Yours firstName");
            table.addCell(visit.getClient().getfName());
            table.addCell("Yours lastName");
            table.addCell(visit.getClient().getlName());
            table.addCell("Account number for payment");
            table.addCell("12312312");
            table.addCell("Doctors first name");
            table.addCell(visit.getDoctor().getAppUser().getfName());
            table.addCell("Doctors last name");
            table.addCell(visit.getDoctor().getAppUser().getlName());
            table.addCell("Visit date");
            table.addCell(String.valueOf(visit.getDate()).substring(0,10));
            table.addCell("Visit price");
            table.addCell(String.valueOf(visit.getPrice()));
            pdf.add(table);
            pdf.close();
            o.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
