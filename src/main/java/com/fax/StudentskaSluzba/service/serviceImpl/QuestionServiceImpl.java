package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Question;
import com.fax.StudentskaSluzba.repository.QuestionRepository;
import com.fax.StudentskaSluzba.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository repository;

    @Override
    public List<Question> saveAll(List<Question> list) {
        return repository.saveAll(list);
    }

    @Override
    public List<Question> getAll(ExamTest examTest) {
        return repository.findAllByExamTest(examTest);
    }
}
