package com.example.backend.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MpesaPaymentService {
    @Autowired
    private MpesaPaymentRepository repository;


    public void processPayment(String name, Long phone, Date date,String transaction, Double amount){
        MpesaPayment mpesaPayment = new MpesaPayment();
        mpesaPayment.setName(name);
        mpesaPayment.setPhone(phone);
        mpesaPayment.setDate(date);
        mpesaPayment.setTransaction(transaction);
        mpesaPayment.setAmount(amount);
        repository.save(mpesaPayment);

    }

    public List<MpesaPayment> findAll() {
        return repository.findAll();

    }
}
