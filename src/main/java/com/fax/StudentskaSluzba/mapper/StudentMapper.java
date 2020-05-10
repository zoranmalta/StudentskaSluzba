package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.modeldto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public StudentDTO toStudentDTO(Student student){
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setCardNumber(student.getCardNumber());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setAccountBalance(student.getAccountBalance(student.getPayments()));
        studentDTO.setEmail(student.getEmail());
        studentDTO.setDeleted(student.isDeleted());
        return studentDTO;
    }

    public Student toStudent(StudentDTO studentDTO){
        Student student=new Student();
        student.setUser(studentDTO.getUserForm());
        student.setAccountBalance(studentDTO.getAccountBalance());
        student.setAddress(studentDTO.getAddress());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setCardNumber(studentDTO.getCardNumber());
        student.setEmail(studentDTO.getEmail());
        student.setDeleted(studentDTO.isDeleted());

        return student;
    }
    public List<StudentDTO> toListStudentDTO(List<Student> students){
        return students.stream().map(student -> toStudentDTO(student))
                .collect(Collectors.toList());
    }

}
