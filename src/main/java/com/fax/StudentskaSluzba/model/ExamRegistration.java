package com.fax.StudentskaSluzba.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(name = "exam_id_student_id"
        , columnNames = {"exam_id"," student_id"}))
public class ExamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    //dodato da formira unique vezu exam i student u tabeli exam_registration
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "exam_application",insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp examApplication;

    private int points;

    private int mark;

    @Column(columnDefinition = "boolean default false")
    private boolean pass;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

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
        ExamRegistration other = (ExamRegistration) obj;
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

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Timestamp getExamApplication() {
        return examApplication;
    }

    public void setExamApplication(Timestamp examApplication) {
        this.examApplication = examApplication;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    @Override
    public String toString() {
        return "ExamRegistration{" +
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
