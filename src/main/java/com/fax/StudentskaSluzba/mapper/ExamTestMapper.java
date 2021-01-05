package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.modeldto.ExamTestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExamTestMapper {
    @Autowired
    private ExamMapper examMapper;

    public ExamTest toExamTest(ExamTestDTO examTestDTO){
        ExamTest e=new ExamTest();
        e.setId(examTestDTO.getId());
        e.setBodovi(examTestDTO.getBodovi());
        e.setTema(examTestDTO.getTema());
        e.setTestStart(examTestDTO.getTestStart());
        e.setTrajanje(examTestDTO.getTrajanje());
        e.setExam(examMapper.toExam(examTestDTO.getExam()));
        e.setDeleted(examTestDTO.isDeleted());
        e.setZavrseno(examTestDTO.isZavrseno());
        e.setDostupno(examTestDTO.isDostupno());
        return e;
    }

    public ExamTestDTO toExamTestDTO(ExamTest examTest){
        ExamTestDTO e=new ExamTestDTO();
        e.setId(examTest.getId());
        e.setTrajanje(examTest.getTrajanje());
        e.setTema(examTest.getTema());
        e.setTestStart(examTest.getTestStart());
        e.setBodovi(examTest.getBodovi());
        e.setExam(examMapper.toExamDTO(examTest.getExam()));
        e.setDeleted(examTest.isDeleted());
        e.setZavrseno(examTest.isZavrseno());
        e.setDostupno(examTest.isDostupno());
        return e;
    }

    public List<ExamTestDTO> toListExamTestDTO(List<ExamTest> list){
        return list.stream().map(examTest -> toExamTestDTO(examTest))
                .collect(Collectors.toList());
    }
}
