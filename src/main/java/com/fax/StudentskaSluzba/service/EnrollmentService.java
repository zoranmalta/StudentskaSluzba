package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.Enrollment;
import com.fax.StudentskaSluzba.model.Student;

import java.util.List;

public interface EnrollmentService {
    public List<Enrollment> getEnrollmentsByCourse(Long courseId);
    public List<Enrollment> saveAll(List<Enrollment> enrollments);
    public Enrollment findOneById(Long id);
}
