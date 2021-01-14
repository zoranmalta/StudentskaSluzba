package com.fax.StudentskaSluzba.modeldto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;


@Data
@NoArgsConstructor
@ToString
public class QuestionDTO {
    private Long id;
    private ExamTestDTO examTest;

    private String pitanje;
    private String odgovor1;
    private String odgovor2;
    private String odgovor3;
    private String odgovor4;
    private String opis;

    private int bodovi;
    private boolean deleted;


}
