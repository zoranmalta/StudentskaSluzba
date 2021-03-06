package com.fax.StudentskaSluzba.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
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
    @Column( columnDefinition = "int default 0")
    private int bodovi;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

}
