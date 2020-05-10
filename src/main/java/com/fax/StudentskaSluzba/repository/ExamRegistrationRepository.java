package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.model.ExamRegistration;
import com.fax.StudentskaSluzba.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRegistrationRepository extends JpaRepository<ExamRegistration,Long> {

    ExamRegistration findByExamAndStudent(Exam exam, Student student);
    List<ExamRegistration> findAllByExam(Exam exam);

    @Query(value = "select er.id,er.points,er.mark,er.pass,er.deleted,er.exam_id,er.student_id,er.exam_application from exam_registration er inner join exam ex on er.exam_id=ex.id " +
            "where ex.archived=true and er.deleted=true and er.pass=true and er.student_id=?1"
            ,nativeQuery = true)
    List<ExamRegistration> fetchArchivedExamRegistrationsByStudentId(Long studentId);
}
