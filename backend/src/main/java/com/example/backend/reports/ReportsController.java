package com.example.backend.reports;

import com.example.backend.payment.MpesaPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class ReportsController {
    @Autowired
    private ReportsService service;

    @GetMapping("/reports/chart")
    @ResponseBody
    public ResponseEntity<byte[]> getChartImage() throws IOException {
        List<MpesaPayment> mpesaPayments = service.findAll();
        byte[] image = service.generateTransactionBarChart(mpesaPayments);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(image,httpHeaders, HttpStatus.OK);
    }
}
