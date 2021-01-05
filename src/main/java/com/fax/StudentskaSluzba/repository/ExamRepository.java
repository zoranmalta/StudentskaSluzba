package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Course;
import com.fax.StudentskaSluzba.model.Enrollment;
import com.fax.StudentskaSluzba.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Long> {

    @Query(value = "select ex.id,ex.course_id,ex.exam_start,ex.period,ex.archived from exam ex "+
            "inner join exam_registration er on ex.id=er.exam_id where ex.archived=false and er.student_id=?1 "
            ,nativeQuery = true)
    List<Exam> fetchExamsByStudentIdNotArchivated(Long studentId);

    @Query(value = "select ex.id,ex.course_id,ex.exam_start,ex.period,ex.archived from exam ex "+
            "inner join course c on ex.course_id=c.id where ex.exam_start > ?2 and  c.id in " +
            "(select c.id from course c inner join enrollment e on c.id=e.course_id where e.student_id=?1)"+
            "and c.id not in (select ex.course_id from exam ex inner join exam_registration er on ex.id=er.exam_id " +
            "    where er.pass=true and er.student_id=?1)"
            ,nativeQuery = true)
    List<Exam> fetchExamsByStudentId(Long studentId, Timestamp timestamp);

    @Query(value ="select ex.id,ex.course_id,ex.exam_start,ex.period,ex.archived from exam ex inner "+
            "join exam_registration er on ex.id=er.exam_id where er.student_id=?1 and ex.id in (select ex.id from exam ex "+
            "inner join course c on ex.course_id=c.id where ex.exam_start > ?2 and c.id in " +
            "(select c.id from course c inner join enrollment e on c.id=e.course_id where e.student_id=?1)" +
            "and c.id not in (select ex.course_id from exam ex inner join exam_registration er on ex.id=er.exam_id " +
            "    where er.pass=true and er.student_id=1))"
            ,nativeQuery = true)
    List<Exam> fetchExamsByStudentIdNotRegistration(Long studentId , Timestamp timestamp);

    @Query(value = "select ex.id,ex.course_id,ex.exam_start,ex.period,ex.archived from exam ex "+
            "inner join course c on ex.course_id=c.id where c.id in " +
            "(select c.id from course c inner join engagement e on c.id=e.course_id where e.staff_id=?1)"
            ,nativeQuery = true)
    List<Exam> fetchExamsByStaffId(Long staffId);

}
