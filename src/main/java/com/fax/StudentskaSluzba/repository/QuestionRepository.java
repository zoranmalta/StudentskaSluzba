package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findAllByExamTest(ExamTest examTest);
    Page<Question> findAllByExamTest(ExamTest examTest, Pageable pageable);
}
