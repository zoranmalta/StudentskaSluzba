package com.fax.StudentskaSluzba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@ToString
public class ExamTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "int default 20")
    private int trajanje;
    @Column(columnDefinition = "int default 0")
    private int bodovi;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Exam exam;

    private String tema;

    @Column(name = "exam_start",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp testStart;

    @JsonIgnore
    @OneToMany(targetEntity = Question.class, mappedBy = "examTest", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<Question>();

    @JsonIgnore
    @OneToMany(targetEntity = WorkTest.class, mappedBy = "examTest", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<WorkTest> workTests=new ArrayList<>();

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @Column(columnDefinition = "boolean default false")
    private boolean dostupno;

    @Column(columnDefinition = "boolean default false")
    private boolean zavrseno;

}
