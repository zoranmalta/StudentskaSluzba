package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.model.ExamTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamTestRepository extends JpaRepository<ExamTest,Long> {

    List<ExamTest> findAllByExam(Exam exam);

}
