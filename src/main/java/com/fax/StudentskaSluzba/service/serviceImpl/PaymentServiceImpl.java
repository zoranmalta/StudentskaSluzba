package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.model.Payment;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.repository.PaymentRepository;
import com.fax.StudentskaSluzba.repository.StudentRepository;
import com.fax.StudentskaSluzba.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Payment> getPaymentsByStudentId(Long studentId) {
        Student student=studentRepository.findOneById(studentId);
        return paymentRepository.findByStudent(student);
    }

    @Override
    public Payment insertPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

}
