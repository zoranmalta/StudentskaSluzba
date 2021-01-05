package com.fax.StudentskaSluzba.service;


import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Question;

import java.util.List;

public interface QuestionService {

    public List<Question> saveAll(List<Question> list);
    public List<Question> getAll(ExamTest examTest);
}
