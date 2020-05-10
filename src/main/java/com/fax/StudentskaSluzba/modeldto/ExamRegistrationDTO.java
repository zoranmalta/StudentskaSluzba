package com.fax.StudentskaSluzba.modeldto;

import java.sql.Timestamp;

public class ExamRegistrationDTO {

    private Long id;
    private ExamDTO exam;
    private StudentDTO student;
    private Timestamp examApplication;
    private int points;
    private int mark;
    private boolean pass;
    private boolean deleted;

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

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public Timestamp getExamApplication() {
        return examApplication;
    }

    public void setExamApplication(Timestamp examApplication) {
        this.examApplication = examApplication;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ExamRegistrationDTO{" +
                "id=" + id +
                ", exam=" + exam +
                ", student=" + student +
                ", examApplication=" + examApplication +
                ", points=" + points +
                ", mark=" + mark +
                ", pass=" + pass +
                ", deleted=" + deleted +
                '}';
    }
}
