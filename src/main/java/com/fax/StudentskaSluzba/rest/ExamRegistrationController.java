package com.fax.StudentskaSluzba.rest;

import com.fax.StudentskaSluzba.mapper.ExamMapper;
import com.fax.StudentskaSluzba.mapper.ExamRegistrationMapper;
import com.fax.StudentskaSluzba.mapper.StudentMapper;
import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.model.ExamRegistration;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.modeldto.ExamRegistrationDTO;
import com.fax.StudentskaSluzba.service.ExamService;
import com.fax.StudentskaSluzba.service.serviceImpl.ExamRegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/examregistration", produces = MediaType.APPLICATION_JSON_VALUE )
public class ExamRegistrationController {
    @Autowired
    private ExamRegistrationMapper examRegistrationMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ExamRegistrationServiceImpl examRegistrationService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ExamService examService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<ExamRegistrationDTO> insertExamRegistration(@RequestBody ExamRegistrationDTO examRegistrationDTO) {

        System.out.println("stiglo sa fronta : "+ examRegistrationDTO.toString());

        ExamRegistration examRegistration=new ExamRegistration();
        Exam exam= examMapper.toExam(examRegistrationDTO.getExam());
        exam.setId(examRegistrationDTO.getExam().getId());
        examRegistration.setExam(exam);
        Student student= studentMapper.toStudent(examRegistrationDTO.getStudent());
        student.setId(examRegistrationDTO.getStudent().getId());
        examRegistration.setStudent(student);
        examRegistration.setExamApplication(examRegistrationDTO.getExamApplication());
        examRegistration.setMark(examRegistrationDTO.getMark());
        examRegistration.setPoints(examRegistrationDTO.getPoints());
        try {
            examRegistration=examRegistrationService.save(examRegistration);
            return new ResponseEntity<>(examRegistrationMapper.toExamRegistrationDTO(examRegistration)
                        , HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.PUT)
    public ResponseEntity<Void> deleteExamRegistration(@RequestBody ExamRegistrationDTO examRegistrationDTO) {
        System.out.println("Stiglo sa servera za delete: "+examRegistrationDTO.toString());
        ExamRegistration examRegistration=examRegistrationMapper.toExamRegistration(examRegistrationDTO);
        examRegistration.getStudent().setId(examRegistrationDTO.getStudent().getId());
        examRegistration.getExam().setId(examRegistrationDTO.getExam().getId());
        System.out.println("Stiglo sa servera za delete: "+examRegistration.toString());
        try {
        examRegistration=examRegistrationService.findOneByExamAndStudent(examRegistration);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        examRegistration.setDeleted(true);
        try {
            examRegistrationService.delete(examRegistration);
            //todo vratiti pare studentu po odjavi ispita delete Payment?
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/exam/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<ExamRegistrationDTO>> getExamRegistrationByExamId(@PathVariable("id") Long id){
        try {
            Exam exam=examService.findOneById(id);
            List<ExamRegistration> examRegistrations=examRegistrationService.findAllByExam(exam);
            return new ResponseEntity<List<ExamRegistrationDTO>>(examRegistrationMapper
                    .toListExamRegistrationDTO(examRegistrations), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/student/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<ExamRegistrationDTO>> getFinishedExamRegistrations(@PathVariable("id") Long id){
        try {
            List<ExamRegistration> examRegistrations=examRegistrationService
                    .findFinishedExamRegistrations(id);
            return new ResponseEntity<List<ExamRegistrationDTO>>(examRegistrationMapper
                    .toListExamRegistrationDTO(examRegistrations), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/updatefinish",method = RequestMethod.PUT)
    public ResponseEntity<ExamRegistrationDTO> finishExamRegistration(@RequestBody ExamRegistrationDTO examRegistrationDTO){
        try {
            ExamRegistration examRegistration=examRegistrationService.findOneById(examRegistrationDTO.getId());
            examRegistration.setDeleted(true);
            examRegistration.setPoints(examRegistrationDTO.getPoints());
            examRegistration.setMark(examRegistrationDTO.getMark());
            examRegistration.setPass(examRegistrationDTO.isPass());
            examRegistration=examRegistrationService.save(examRegistration);
            return new ResponseEntity<>(examRegistrationMapper.toExamRegistrationDTO(examRegistration)
                    ,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
