package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.modeldto.ExamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ExamMapper {
    @Autowired
    private CourseMapper courseMapper;

    public Exam toExam(ExamDTO examDTO){
        Exam exam=new Exam();
        exam.setPeriod(examDTO.getPeriod());
        exam.setCourse(courseMapper.toCourse(examDTO.getCourse()));
        exam.setExamStart(examDTO.getExamStart());

        return exam;
    }

    public ExamDTO toExamDTO(Exam exam){
        ExamDTO examDTO=new ExamDTO();
        examDTO.setId(exam.getId());
        examDTO.setPeriod(exam.getPeriod());
        examDTO.setCourse(courseMapper.toCourseDTO(exam.getCourse()));
        examDTO.setExamStart(exam.getExamStart());
        examDTO.setArchived(exam.isArchived());

        return examDTO;
    }

    public List<ExamDTO> toExamDTOList(List<Exam> examList){
        return examList.stream().map(this::toExamDTO).collect(Collectors.toList());
    }
}
