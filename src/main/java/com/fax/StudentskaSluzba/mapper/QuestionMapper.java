package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.Question;
import com.fax.StudentskaSluzba.modeldto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {
    @Autowired
    private ExamTestMapper examTestMapper;

    public Question toQuestion(QuestionDTO questionDTO){
        Question q=new Question();
        q.setId(questionDTO.getId());
        q.setExamTest(examTestMapper.toExamTest(questionDTO.getExamTest()));
        q.setDeleted(questionDTO.isDeleted());
        q.setPitanje(questionDTO.getPitanje());
        q.setOdgovor1(questionDTO.getOdgovor1());
        q.setOdgovor2(questionDTO.getOdgovor2());
        q.setOdgovor3(questionDTO.getOdgovor3());
        q.setOdgovor4(questionDTO.getOdgovor4());
        q.setOpis(questionDTO.getOpis());
        return q;
    }
    public QuestionDTO toQuestionDTO(Question question){
        QuestionDTO q=new QuestionDTO();
        q.setId(question.getId());
        q.setExamTest(examTestMapper.toExamTestDTO(question.getExamTest()));
        q.setDeleted(question.isDeleted());
        q.setPitanje(question.getPitanje());
        q.setOdgovor1(question.getOdgovor1());
        q.setOdgovor2(question.getOdgovor2());
        q.setOdgovor3(question.getOdgovor3());
        q.setOdgovor4(question.getOdgovor4());
        q.setOpis(question.getOpis());
        return q;
    }
    public List<QuestionDTO> toListQuestionDTO(List<Question> list){
        return list.stream().map(question -> toQuestionDTO(question))
                .collect(Collectors.toList());
    }
}
