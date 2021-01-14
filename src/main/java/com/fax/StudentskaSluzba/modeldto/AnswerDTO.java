package com.fax.StudentskaSluzba.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

    private Long id;

    private WorkTestDTO workTest;

    private String odgovor;

    private boolean tacan;

 ;
}
