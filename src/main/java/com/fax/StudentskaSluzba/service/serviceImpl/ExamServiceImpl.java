package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.model.Course;
import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.model.ExamRegistration;
import com.fax.StudentskaSluzba.repository.ExamRepository;
import com.fax.StudentskaSluzba.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Exam> getAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam insertExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam archiveExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam findOneById(Long id) {
        return examRepository.getOne(id);
    }

    @Override
    public List<Exam> getExamsByStudentId(Long studentId, Timestamp timestamp) {
        return examRepository.fetchExamsByStudentId(studentId,timestamp);
    }

    @Override
    public List<Exam> getExamsByStudentIdNotRegistration(Long studentId, Timestamp timestamp) {
        return examRepository.fetchExamsByStudentIdNotRegistration(studentId,timestamp);
    }

    @Override
    public List<Exam> getExamsByStaff(Long staffId) {
        return examRepository.fetchExamsByStaffId(staffId);
    }


}
