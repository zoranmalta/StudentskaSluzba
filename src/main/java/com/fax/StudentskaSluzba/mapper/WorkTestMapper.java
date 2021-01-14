package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.WorkTest;
import com.fax.StudentskaSluzba.modeldto.WorkTestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkTestMapper {
    @Autowired
    private ExamTestMapper examTestMapper;
    @Autowired
    private StudentMapper studentMapper;

    public WorkTest toWorkTest(WorkTestDTO workTestDTO){
        WorkTest w=new WorkTest();
        if(workTestDTO.getId()!=null){

        w.setId(workTestDTO.getId());
        }
        w.setBodovi(workTestDTO.getBodovi());
        w.setExamTest(examTestMapper.toExamTest(workTestDTO.getExamTest()));
        w.setStudent(studentMapper.toStudent(workTestDTO.getStudent()));

        return w;
    }

    public WorkTestDTO toWorkTestDTO(WorkTest workTest){
        WorkTestDTO w=new WorkTestDTO();
        w.setId(workTest.getId());
        w.setBodovi(workTest.getBodovi());
        w.setExamTest(examTestMapper.toExamTestDTO(workTest.getExamTest()));
        w.setStudent(studentMapper.toStudentDTO(workTest.getStudent()));

        return w;
    }

    public List<WorkTestDTO> toListWorkTestDTO(List<WorkTest> list){
        return list.stream().map(workTest -> toWorkTestDTO(workTest)).collect(Collectors.toList());
    }
}
