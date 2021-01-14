package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.Answer;
import com.fax.StudentskaSluzba.model.WorkTest;

import java.util.List;

public interface AnswerService {
    List<Answer>  insertAnswers(List<Answer> answerList);
    List<Answer>  getAnswersByWorkTest(WorkTest workTest);
}
