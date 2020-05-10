package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Staff;
import com.fax.StudentskaSluzba.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    @Query(value = "SELECT distinct s.id,s.first_name,s.last_name,s.deleted,s.academic_id,s.user_id"+
            " FROM Staff s LEFT JOIN engagement e ON s.id=e.staff_id  "+
            " where s.id not in (SELECT distinct s.id FROM Staff s INNER JOIN engagement e "+
            " ON s.id=e.staff_id AND e.course_id=?1 where e.deleted=false )  ",nativeQuery = true)
    List<Staff> fetchStaffFalseCourseLeftJoin(Long courseId);

    Staff findByUser(User user);

}
