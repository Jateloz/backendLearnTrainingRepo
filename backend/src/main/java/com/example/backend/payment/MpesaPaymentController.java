package com.example.backend.payment;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class MpesaPaymentController {
    @Autowired
    private MpesaPaymentService service;

    public MpesaPaymentController(MpesaPaymentService service) {
        this.service = service;
    }

    @PostMapping("/mpesaPayment")
    public String mpesaPayment(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("date") Date date,
            @RequestParam("transaction") String transaction,
            @RequestParam("amount") Double amount
    ){
        service.processPayment(name, Long.valueOf(phone),date,transaction,amount);
        return "redirect:/paymentSuccess";
    }
    @GetMapping("/reports/download")
    public void downloadReport(HttpServletResponse response) throws IOException{
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition","attachment; filename=mpesa_reports.csv");

        List<MpesaPayment> mpesaPayments = service.findAll();

        PrintWriter printWriter = response.getWriter();
        printWriter.println("Date,Name,Phone,Transaction,Amount");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (MpesaPayment p : mpesaPayments){
            String formattedDate = (p.getDate() != null)?simpleDateFormat.format(p.getDate()):"N/A";
            printWriter.printf("%s,%s,%s,%s,%.2f%n",formattedDate,p.getName(),p.getPhone(),p.getTransaction(),p.getAmount());
        }
        printWriter.flush();
        printWriter.close();

    }
}
