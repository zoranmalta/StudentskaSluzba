package com.fax.StudentskaSluzba.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Student student;

    @Column(name = "payment_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp paymentTime;

    private String reason;

    private long onePaymentChange;

    @Override
    public String toString() {
        return "Payment123{" +
                "id=" + id +
                ", student=" + student +
                ", paymentTime=" + paymentTime +
                ", reason='" + reason + '\'' +
                ", onePaymentChange=" + onePaymentChange +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
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
}
