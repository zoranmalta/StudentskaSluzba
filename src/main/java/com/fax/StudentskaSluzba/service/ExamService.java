package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.Course;
import com.fax.StudentskaSluzba.model.Exam;

import java.sql.Timestamp;
import java.util.List;

public interface ExamService {

    List<Exam> getAll();
    Exam insertExam(Exam exam);
    Exam findOneById(Long id);
    List<Exam> getExamsByStudentIdNotArchivated(Long studentId);
    List<Exam> getExamsByStudentId(Long studentId, Timestamp timestamp);
    List<Exam> getExamsByStudentIdNotRegistration(Long studentId , Timestamp timestamp);
    List<Exam> getExamsByStaff(Long staffId);

}
