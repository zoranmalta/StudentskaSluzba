package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EngagementRepository extends JpaRepository<Engagement,Long> {
    @Query(value = "SELECT * "
            + "FROM Engagement e WHERE e.course_id=?1  ",nativeQuery = true)
    List<Engagement> fetchEngagementByCourse(Long courseId);
}
