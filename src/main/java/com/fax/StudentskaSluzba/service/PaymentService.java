package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getPaymentsByStudentId(Long studentId);
    Payment insertPayment(Payment payment);
}
