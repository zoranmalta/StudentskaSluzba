package com.fax.StudentskaSluzba.modeldto;

import com.fax.StudentskaSluzba.model.Student;

import java.sql.Timestamp;

public class PaymentDTO {

    private Long id;

    private StudentDTO student;

    private Timestamp paymentTime;

    private String reason;

    private long onePaymentChange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getOnePaymentChange() {
        return onePaymentChange;
    }

    public void setOnePaymentChange(long onePaymentChange) {
        this.onePaymentChange = onePaymentChange;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", student=" + student +
                ", paymentTime=" + paymentTime +
                ", reason='" + reason + '\'' +
                ", onePaymentChange=" + onePaymentChange +
                '}';
    }
}
