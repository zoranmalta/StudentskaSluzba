package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Payment;
import com.fax.StudentskaSluzba.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudent(Student student);
}
