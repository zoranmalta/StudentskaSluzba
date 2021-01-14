package com.fax.StudentskaSluzba.modeldto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
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

}
