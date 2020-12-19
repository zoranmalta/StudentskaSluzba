package com.fax.StudentskaSluzba.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private ExamTest examTest;

    private String pitanje;
    private String odgovor1;
    private String odgovor2;
    private String odgovor3;
    private String odgovor4;
    private String opis;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExamTest getExamTest() {
        return examTest;
    }

    public void setExamTest(ExamTest examTest) {
        this.examTest = examTest;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getOdgovor1() {
        return odgovor1;
    }

    public void setOdgovor1(String odgovor1) {
        this.odgovor1 = odgovor1;
    }

    public String getOdgovor2() {
        return odgovor2;
    }

    public void setOdgovor2(String odgovor2) {
        this.odgovor2 = odgovor2;
    }

    public String getOdgovor3() {
        return odgovor3;
    }

    public void setOdgovor3(String odgovor3) {
        this.odgovor3 = odgovor3;
    }

    public String getOdgovor4() {
        return odgovor4;
    }

    public void setOdgovor4(String odgovor4) {
        this.odgovor4 = odgovor4;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", examTest=" + examTest +
                ", pitanje='" + pitanje + '\'' +
                ", odgovor1='" + odgovor1 + '\'' +
                ", odgovor2='" + odgovor2 + '\'' +
                ", odgovor3='" + odgovor3 + '\'' +
                ", odgovor4='" + odgovor4 + '\'' +
                ", opis='" + opis + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}