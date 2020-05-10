package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.Payment;
import com.fax.StudentskaSluzba.modeldto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {
    @Autowired
    private StudentMapper studentMapper;

    public PaymentDTO toPaymentDTO(Payment payment){
        PaymentDTO paymentDTO=new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setOnePaymentChange(payment.getOnePaymentChange());
        paymentDTO.setPaymentTime(payment.getPaymentTime());
        paymentDTO.setReason(payment.getReason());
        paymentDTO.setStudent(studentMapper.toStudentDTO(payment.getStudent()));
        return paymentDTO;
    }

    public Payment toPayment(PaymentDTO paymentDTO){
        Payment payment= new Payment();
        payment.setId(paymentDTO.getId());
        payment.setOnePaymentChange(paymentDTO.getOnePaymentChange());
        payment.setPaymentTime(paymentDTO.getPaymentTime());
        payment.setReason(paymentDTO.getReason());
        payment.setStudent(studentMapper.toStudent(paymentDTO.getStudent()));
        return payment;
    }

    public List<PaymentDTO> toListPaymentDTO(List<Payment> payments){
        return payments.stream().map(payment -> toPaymentDTO(payment)).collect(Collectors.toList());
    }
}
