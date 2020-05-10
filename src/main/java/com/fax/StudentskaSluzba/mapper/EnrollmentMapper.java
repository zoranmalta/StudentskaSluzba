package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.Enrollment;
import com.fax.StudentskaSluzba.modeldto.EnrollmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class EnrollmentMapper {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMapper courseMapper;

    public Enrollment toEnrollment(EnrollmentDTO enrollmentDTO){
        Enrollment enrollment= new Enrollment();
        enrollment.setId(enrollmentDTO.getId());
        enrollment.setDeleted(enrollmentDTO.isDeleted());
        enrollment.setStudent(studentMapper.toStudent(enrollmentDTO.getStudent()));
        enrollment.setCourse(courseMapper.toCourse(enrollmentDTO.getCourse()));
        return enrollment;
    }
    public EnrollmentDTO toEnrollmentDTO(Enrollment enrollment){
        EnrollmentDTO enrollmentDTO=new EnrollmentDTO();
        enrollmentDTO.setId(enrollment.getId());
        enrollmentDTO.setDeleted(enrollment.isDeleted());
        enrollmentDTO.setStudent(studentMapper.toStudentDTO(enrollment.getStudent()));
        enrollmentDTO.setCourse(courseMapper.toCourseDTO(enrollment.getCourse()));
        return enrollmentDTO;
    }
    public List<EnrollmentDTO> toEnrollmentDTOList(List<Enrollment> enrollments){
        return enrollments.stream().map(enrollment -> toEnrollmentDTO(enrollment))
                .collect(Collectors.toList());
    }
}
