package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "SELECT c.id , c.name , c.deleted "+
            " FROM Course c INNER JOIN enrollment e ON c.id=e.course_id AND e.student_id=?1 "+
            "where  e.deleted=false AND c.deleted=false  ",nativeQuery = true)
    List<Course> fetchCourseByStudentIdAndNotDeletedEnrollment(Long studentId);
}
