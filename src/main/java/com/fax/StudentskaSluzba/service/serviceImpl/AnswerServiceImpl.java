package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.model.Answer;
import com.fax.StudentskaSluzba.model.WorkTest;
import com.fax.StudentskaSluzba.repository.AnswerRepository;
import com.fax.StudentskaSluzba.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Override
    public List<Answer> insertAnswers(List<Answer> answerList) {
        return answerRepository.saveAll(answerList);
    }

    @Override
    public List<Answer> getAnswersByWorkTest(WorkTest workTest) {
        return answerRepository.findAllByWorkTest(workTest);
    }
}
