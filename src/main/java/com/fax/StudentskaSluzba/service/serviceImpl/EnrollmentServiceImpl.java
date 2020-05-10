package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.model.Enrollment;
import com.fax.StudentskaSluzba.repository.EnrollmentRepository;
import com.fax.StudentskaSluzba.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public List<Enrollment> getEnrollmentsByCourse( Long courseId) {
        return enrollmentRepository.fetchEnrollmentByCourse(courseId);
    }

    @Override
    public List<Enrollment> saveAll(List<Enrollment> enrollments) {
        return enrollmentRepository.saveAll(enrollments);
    }

    @Override
    public Enrollment findOneById(Long id) {
        return enrollmentRepository.getOne(id);
    }


}
