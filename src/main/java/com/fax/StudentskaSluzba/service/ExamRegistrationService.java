package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.model.ExamRegistration;

import java.util.List;

public interface ExamRegistrationService {

    ExamRegistration save(ExamRegistration examRegistration);
    ExamRegistration findOneById(Long id);
    void delete(ExamRegistration examRegistration);
    ExamRegistration findOneByExamAndStudent(ExamRegistration examRegistration);
    List<ExamRegistration> findAllByExam(Exam exam);
    List<ExamRegistration> findFinishedExamRegistrations(Long studentId);
}
