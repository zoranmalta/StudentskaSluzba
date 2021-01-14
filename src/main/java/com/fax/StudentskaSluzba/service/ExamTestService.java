package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Question;
import com.fax.StudentskaSluzba.modeldto.ExamTestDTO;
import com.fax.StudentskaSluzba.modeldto.QuestionDTO;

import java.util.List;

public interface ExamTestService {

    public ExamTest save(ExamTest examTest);
    public List<ExamTest> getAllByExam(Exam exam);
    public ExamTestDTO saveExamTest(ExamTest examTestDTO,List<QuestionDTO> list);
    public ExamTest getOneById(Long examTestId);
}
