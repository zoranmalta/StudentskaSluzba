package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    @Query(value = "SELECT * "
            + "FROM Enrollment e WHERE e.course_id=?1  ",nativeQuery = true)
    List<Enrollment> fetchEnrollmentByCourse(Long courseId);
}
