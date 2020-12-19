package com.fax.StudentskaSluzba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ExamTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Exam exam;

    @JsonIgnore
    @OneToMany(targetEntity = Question.class, mappedBy = "examTest", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<Question>();

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ExamTest{" +
                "id=" + id +
                ", exam=" + exam +
                ", questions=" + questions +
                ", deleted=" + deleted +
                '}';
    }
}
