package com.fax.StudentskaSluzba.service;


import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {

    public List<Question> saveAll(List<Question> list);
    public List<Question> getAll(ExamTest examTest);
    Page<Question> getAllByExam(ExamTest examTest, Pageable pageable);

}
