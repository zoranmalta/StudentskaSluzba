package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.mapper.ExamTestMapper;
import com.fax.StudentskaSluzba.mapper.QuestionMapper;
import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Question;
import com.fax.StudentskaSluzba.modeldto.ExamTestDTO;
import com.fax.StudentskaSluzba.modeldto.QuestionDTO;
import com.fax.StudentskaSluzba.repository.ExamTestRepository;
import com.fax.StudentskaSluzba.service.ExamTestService;
import com.fax.StudentskaSluzba.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamTestServiceImpl implements ExamTestService {

    @Autowired
    private ExamTestRepository examTestRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExamTestMapper examTestMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public ExamTest save(ExamTest examTest) {
        return examTestRepository.save(examTest);
    }

    @Override
    public List<ExamTest> getAllByExam(Exam exam) {
        return examTestRepository.findAllByExam(exam);
    }

    @Override
    @Transactional
    public ExamTestDTO saveExamTest(ExamTest examTest, List<QuestionDTO> list){
        ExamTest e=examTestRepository.save(examTest);
        List<Question> q=questionMapper.toListQuestion(list);
        q=q.stream().map(question -> {question.setExamTest(e);return question;}).collect(Collectors.toList());
        List<Question> list1=questionService.saveAll(q);
        ExamTestDTO examTestDTO=examTestMapper.toExamTestDTO(e);
        examTestDTO.setQuestions(questionMapper.toListQuestionDTO(list1));
        return examTestDTO;
    }
}
