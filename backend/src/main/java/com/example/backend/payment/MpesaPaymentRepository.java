package com.example.backend.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MpesaPaymentRepository extends JpaRepository<MpesaPayment,Long> {
    List<MpesaPayment> findByDateBetween(LocalDate start,LocalDate end);
}
