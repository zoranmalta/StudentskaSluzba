package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.ExamRegistration;
import com.fax.StudentskaSluzba.modeldto.ExamRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExamRegistrationMapper {
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private StudentMapper studentMapper;

    public ExamRegistrationDTO toExamRegistrationDTO(ExamRegistration examRegistration){
        ExamRegistrationDTO examRegistrationDTO=new ExamRegistrationDTO();
        examRegistrationDTO.setId(examRegistration.getId());
        examRegistrationDTO.setDeleted(examRegistration.isDeleted());
        examRegistrationDTO.setExamApplication(examRegistration.getExamApplication());
        examRegistrationDTO.setStudent(studentMapper.toStudentDTO(examRegistration.getStudent()));
        examRegistrationDTO.setExam(examMapper.toExamDTO(examRegistration.getExam()));
        examRegistrationDTO.setMark(examRegistration.getMark());
        examRegistrationDTO.setPoints(examRegistration.getPoints());
        examRegistrationDTO.setPass(examRegistration.isPass());
        return examRegistrationDTO;
    }

    public ExamRegistration toExamRegistration(ExamRegistrationDTO examRegistrationDTO){
        ExamRegistration examRegistration= new ExamRegistration();
        examRegistration.setStudent(studentMapper.toStudent(examRegistrationDTO.getStudent()));
        examRegistration.setExam(examMapper.toExam(examRegistrationDTO.getExam()));
        examRegistration.setPoints(examRegistrationDTO.getPoints());
        examRegistration.setMark(examRegistrationDTO.getMark());
        examRegistration.setPass(examRegistrationDTO.isPass());
        examRegistration.setExamApplication(examRegistrationDTO.getExamApplication());
        examRegistration.setId(examRegistrationDTO.getId());
        examRegistration.setDeleted(examRegistrationDTO.isDeleted());
        return examRegistration;
    }
    public List<ExamRegistrationDTO> toListExamRegistrationDTO(List<ExamRegistration> examRegistrations){
        return examRegistrations.stream().map(examRegistration -> toExamRegistrationDTO(examRegistration))
                .collect(Collectors.toList());
    }
}
