package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.model.WorkTest;
import com.fax.StudentskaSluzba.modeldto.AnswerDTO;
import com.fax.StudentskaSluzba.modeldto.WorkTestDTO;

import java.util.List;

public interface WorkTestService {

    WorkTestDTO insertWorkTest(WorkTest workTest, List<AnswerDTO> list);
    List<WorkTest> getAllByExamTest(ExamTest examTest);
    WorkTest getOneByExamTestAndStudent(ExamTest examTest, Student student);
    WorkTest getOneById(Long id);
}
