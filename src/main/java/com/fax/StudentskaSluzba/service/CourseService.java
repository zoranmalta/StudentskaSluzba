package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    public List<Course> findAll();
    Course insertCourse(Course course);
    Course updateCourse(Course course);
    Course findOneById(Long id);

}
