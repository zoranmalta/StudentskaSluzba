package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.Answer;
import com.fax.StudentskaSluzba.modeldto.AnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnswerMapper {
    @Autowired
    private WorkTestMapper workTestMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public Answer toAnswer(AnswerDTO answerDTO){
        Answer a=new Answer();
        a.setId(answerDTO.getId());
        a.setOdgovor(answerDTO.getOdgovor());
        a.setTacan(answerDTO.isTacan());
        if(answerDTO.getWorkTest()!=null){
        a.setWorkTest(workTestMapper.toWorkTest(answerDTO.getWorkTest()));

        }

        return a;
    }

    public AnswerDTO toAnswerDTO(Answer answer){
        AnswerDTO a=new AnswerDTO();
        a.setId(answer.getId());
        a.setOdgovor(answer.getOdgovor());
        a.setTacan(answer.isTacan());
        a.setWorkTest(workTestMapper.toWorkTestDTO(answer.getWorkTest()));

        return a;
    }

    public List<AnswerDTO> toListAnswerDTO(List<Answer> list){
        return list.stream().map(answer -> toAnswerDTO(answer)).collect(Collectors.toList());
    }
    public List<Answer> toListAnswer(List<AnswerDTO> list){
        return list.stream().map(answerDTO -> toAnswer(answerDTO)).collect(Collectors.toList());
    }
}
