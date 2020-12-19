package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.model.*;
import com.fax.StudentskaSluzba.repository.CourseRepository;
import com.fax.StudentskaSluzba.repository.EngagementRepository;
import com.fax.StudentskaSluzba.repository.EnrollmentRepository;
import com.fax.StudentskaSluzba.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    public CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private EngagementRepository engagementRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course insertCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findOneById(Long id) {
        return courseRepository.getOne(id);
    }

    @Override
    public List<Course> findCoursesByStudentId(Long studentId) {
        return courseRepository.fetchCourseByStudentIdAndNotDeletedEnrollment(studentId);
    }

    @Transactional
    public void CourseDeleteTransaction(Course course, List<Enrollment> enrollments, List<Engagement> engagements){
        course=courseRepository.save(course);
        enrollments=enrollmentRepository.saveAll(enrollments);
        engagements=engagementRepository.saveAll(engagements);
    }
}
