package com.fax.StudentskaSluzba.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkTestDTO {

    private Long id;

    private StudentDTO student;

    private ExamTestDTO examTest;

    private int bodovi;

    private List<AnswerDTO> answers = new ArrayList<>();
}
