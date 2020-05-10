package com.fax.StudentskaSluzba.rest;

import com.fax.StudentskaSluzba.mapper.CourseMapper;
import com.fax.StudentskaSluzba.mapper.ExamMapper;
import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.modeldto.ExamDTO;
import com.fax.StudentskaSluzba.service.CourseService;
import com.fax.StudentskaSluzba.service.ExamService;
import com.fax.StudentskaSluzba.service.serviceImpl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Executable;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/exams", produces = MediaType.APPLICATION_JSON_VALUE )
public class ExamController {
    @Autowired
    private ExamServiceImpl examService;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseMapper courseMapper;

    @RequestMapping(value = "all",method = RequestMethod.GET)
    public ResponseEntity<List<ExamDTO>> getAllExams(){

        try {
            List<Exam> list=examService.getAll();
                return new ResponseEntity<>(examMapper.toExamDTOList(list), HttpStatus.OK);
        }catch (Exception e ){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public ResponseEntity<ExamDTO> insertExam(@RequestBody ExamDTO examDTO){
        System.out.println("stiglo sa servera za insert : " + examDTO.toString());

        Exam exam =new Exam();
        exam.setCourse(courseMapper.toCourse(examDTO.getCourse()));
        exam.setExamStart(examDTO.getExamStart());
        exam.setPeriod(examDTO.getPeriod());

        try {
            exam=examService.insertExam(exam);
            return new ResponseEntity<>(examMapper.toExamDTO(exam),HttpStatus.OK);
        }catch (DataIntegrityViolationException ed){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "archive",method = RequestMethod.PUT)
    public ResponseEntity<ExamDTO> archiveExam(@RequestBody ExamDTO examDTO){
        try {
            Exam exam=examService.findOneById(examDTO.getId());
            exam.setArchived(true);
            exam=examService.archiveExam(exam);
            return new ResponseEntity<>(examMapper.toExamDTO(exam),HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "all/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<ExamDTO>> getExamsByStudentId(@PathVariable("id") Long id){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            List<Exam> exams=examService.getExamsByStudentId(id,timestamp);
            return new ResponseEntity<>(examMapper.toExamDTOList(exams), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "staff/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<ExamDTO>> getExamsByStaff(@PathVariable("id") Long id){
        try {
            List<Exam> exams=examService.getExamsByStaff(id);
            return new ResponseEntity<>(examMapper.toExamDTOList(exams), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "allnotregistration/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<ExamDTO>> getExamsByStudentIdNotRegistration(@PathVariable("id") Long id){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            List<Exam> exams=examService.getExamsByStudentIdNotRegistration(id,timestamp);
            return new ResponseEntity<>(examMapper.toExamDTOList(exams), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
