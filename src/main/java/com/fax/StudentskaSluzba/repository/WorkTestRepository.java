package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.model.WorkTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkTestRepository extends JpaRepository<WorkTest,Long> {

    List<WorkTest> findAllByExamTest(ExamTest examTest);
    WorkTest findByExamTestAndStudent(ExamTest examTest,Student student);
}
