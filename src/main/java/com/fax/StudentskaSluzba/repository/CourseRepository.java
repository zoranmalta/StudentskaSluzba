package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
