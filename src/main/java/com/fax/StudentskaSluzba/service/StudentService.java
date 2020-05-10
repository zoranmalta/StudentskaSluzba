package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.Course;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.model.User;
import com.fax.StudentskaSluzba.modeldto.StudentDTO;
import com.fax.StudentskaSluzba.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student findOneById(Long id);
    List<Student> findAll();
    Page<Student> findAll(Pageable pageable);
    Student save(Student student);
    void remove(Long id);
    Optional<Student> findOneByCardNumber(String cardNumber);
    List<Student> findAllByLastName(String lastName);
    List<Student> findAllByCourse(Long courseId);
    List<Student> saveAll(List<Student> students);
    List<Student> freeStudentsByCourse(Long courseId);
    Student findByUser(User user);

}
