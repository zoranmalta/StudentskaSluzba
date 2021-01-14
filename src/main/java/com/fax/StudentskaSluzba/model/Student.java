package com.fax.StudentskaSluzba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cardNumber", unique = true, nullable = false)
    private String cardNumber;

    private String firstName;
    private String lastName;
    private Long accountBalance=0L;
    private String address;
    private String email;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @OneToOne(cascade=CascadeType.REFRESH)
    private User user;
//
//    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonIgnore
    @OneToMany(targetEntity = Enrollment.class, mappedBy = "student", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Enrollment> enrollments = new HashSet<Enrollment>();

    @JsonIgnore
    @OneToMany(targetEntity = ExamRegistration.class, mappedBy = "student", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<ExamRegistration> examRegistrations = new HashSet<ExamRegistration>();

    @JsonIgnore
    @OneToMany(targetEntity = WorkTest.class, mappedBy = "student", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<WorkTest> workTests=new ArrayList<>();

    @JsonIgnore
    @OneToMany(targetEntity = Payment.class, mappedBy = "student", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Payment> payments = new HashSet<Payment>();


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAccountBalance(Set<Payment> payments) {
        for (Payment p :
                payments) {
            this.accountBalance += p.getOnePaymentChange();
        }
        return this.accountBalance;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Set<ExamRegistration> getExamRegistrations() {
        return examRegistrations;
    }

    public void setExamRegistrations(Set<ExamRegistration> examRegistrations) {
        this.examRegistrations = examRegistrations;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountBalance=" + accountBalance +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", deleted=" + deleted +
                ", user=" + user +
                '}';
    }
}
