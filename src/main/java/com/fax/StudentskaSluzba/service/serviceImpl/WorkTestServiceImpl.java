package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.mapper.AnswerMapper;
import com.fax.StudentskaSluzba.mapper.WorkTestMapper;
import com.fax.StudentskaSluzba.model.Answer;
import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.model.WorkTest;
import com.fax.StudentskaSluzba.modeldto.AnswerDTO;
import com.fax.StudentskaSluzba.modeldto.WorkTestDTO;
import com.fax.StudentskaSluzba.repository.WorkTestRepository;
import com.fax.StudentskaSluzba.service.AnswerService;
import com.fax.StudentskaSluzba.service.WorkTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkTestServiceImpl implements WorkTestService {
    @Autowired
    private WorkTestRepository workTestRepository;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private WorkTestMapper workTestMapper;

    @Override
    @Transactional
    public WorkTestDTO insertWorkTest(WorkTest workTest, List<AnswerDTO> list) {
        WorkTest w=workTestRepository.save(workTest);
        List<Answer> answers=answerMapper.toListAnswer(list);
        answers=answers.stream().map(answer -> {answer.setWorkTest(w);return answer;}).collect(Collectors.toList());
        List<Answer> list1=answerService.insertAnswers(answers);
        WorkTestDTO workTestDTO=workTestMapper.toWorkTestDTO(w);
        workTestDTO.setAnswers(answerMapper.toListAnswerDTO(list1));
        return workTestDTO;
    }

    @Override
    public List<WorkTest> getAllByExamTest(ExamTest examTest) {
        return workTestRepository.findAllByExamTest(examTest);
    }

    @Override
    public WorkTest getOneByExamTestAndStudent(ExamTest examTest, Student student) {
        return workTestRepository.findByExamTestAndStudent(examTest,student);
    }

    @Override
    public WorkTest getOneById(Long id) {
        return workTestRepository.getOne(id);
    }
}
