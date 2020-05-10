package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.mapper.ExamMapper;
import com.fax.StudentskaSluzba.mapper.ExamRegistrationMapper;
import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.model.ExamRegistration;
import com.fax.StudentskaSluzba.modeldto.ExamRegistrationDTO;
import com.fax.StudentskaSluzba.repository.ExamRegistrationRepository;
import com.fax.StudentskaSluzba.service.ExamRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRegistrationServiceImpl implements ExamRegistrationService {
    @Autowired
    private ExamRegistrationRepository examRegistrationRepository;

    @Override
    public ExamRegistration save(ExamRegistration examRegistration) {
        return examRegistrationRepository.save(examRegistration);
    }

    @Override
    public ExamRegistration findOneById(Long id) {
        return examRegistrationRepository.getOne(id);
    }

    @Override
    public void delete(ExamRegistration examRegistration) {
         examRegistrationRepository.delete(examRegistration);
    }

    @Override
    public ExamRegistration findOneByExamAndStudent(ExamRegistration examRegistration) {
        return examRegistrationRepository.findByExamAndStudent(examRegistration.getExam()
                ,examRegistration.getStudent());
    }

    @Override
    public List<ExamRegistration> findAllByExam(Exam exam) {
        return examRegistrationRepository.findAllByExam(exam);
    }

    @Override
    public List<ExamRegistration> findFinishedExamRegistrations(Long studentId) {
        return examRegistrationRepository.fetchArchivedExamRegistrationsByStudentId(studentId);
    }


}
