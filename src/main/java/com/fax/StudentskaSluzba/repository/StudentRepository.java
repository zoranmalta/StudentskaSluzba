package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Course;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.model.User;
import com.fax.StudentskaSluzba.modeldto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findOneById(Long id);

    Optional<Student> findOneByCardNumber(String cardNumber);
    List<Student> findAllByLastName(String lastName);
    @Query(value = "SELECT * "
            + "FROM Student s INNER JOIN enrollment e ON s.id=e.student_id AND e.course_id=?1  ",nativeQuery = true)
    List<Student> fetchStudentsByCourseInnerJoin(Long courseId);

    @Query(value = "SELECT distinct s.id,s.first_name,s.last_name,s.card_number,s.address,s.email,s.deleted,s.account_balance,s.user_id"+
            " FROM Student s LEFT JOIN enrollment e ON s.id=e.student_id AND e.course_id!=?1 "+
            "where s.id not in (SELECT distinct s.id FROM Student s INNER JOIN enrollment e "+
            "ON s.id=e.student_id AND e.course_id=?1 where e.deleted=false )  ",nativeQuery = true)
    List<Student> fetchStudentsByFalseCourseInnerJoin(Long courseId);
    Student findByUser(User user);

    //new com.fax.StudentskaSluzba.modeldto.StudentDTO(s.id,s.first_name,s.last_name,s.card_number,s.address,s.account_balance,s.deleted)
    //INNER JOIN enrollment e ON s.id=e.student_id AND e.course_id=1
}
