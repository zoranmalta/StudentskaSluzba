package com.fax.StudentskaSluzba.modeldto;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ExamTestDTO {

    private Long id;
    private ExamDTO exam;
    private int trajanje;
    private int bodovi;
    private String tema;
    private Timestamp testStart;
    private boolean deleted;
    private boolean dostupno;
    private boolean zavrseno;
    private List<QuestionDTO> questions=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExamDTO getExam() {
        return exam;
    }

    public void setExam(ExamDTO exam) {
        this.exam = exam;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Timestamp getTestStart() {
        return testStart;
    }

    public void setTestStart(Timestamp testStart) {
        this.testStart = testStart;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDostupno() {
        return dostupno;
    }

    public void setDostupno(boolean dostupno) {
        this.dostupno = dostupno;
    }

    public boolean isZavrseno() {
        return zavrseno;
    }

    public void setZavrseno(boolean zavrseno) {
        this.zavrseno = zavrseno;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "ExamTestDTO{" +
                "id=" + id +
                ", exam=" + exam +
                ", trajanje=" + trajanje +
                ", bodovi=" + bodovi +
                ", tema='" + tema + '\'' +
                ", testStart=" + testStart +
                ", deleted=" + deleted +
                ", dostupno=" + dostupno +
                ", zavrseno=" + zavrseno +
                ", questions=" + questions +
                '}';
    }
}
