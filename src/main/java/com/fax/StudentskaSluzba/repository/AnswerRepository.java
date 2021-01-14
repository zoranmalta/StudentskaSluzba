package com.fax.StudentskaSluzba.repository;

import com.fax.StudentskaSluzba.model.Answer;
import com.fax.StudentskaSluzba.model.WorkTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findAllByWorkTest(WorkTest workTest);
}
