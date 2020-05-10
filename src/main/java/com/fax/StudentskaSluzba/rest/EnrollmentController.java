package com.fax.StudentskaSluzba.rest;

import com.fax.StudentskaSluzba.mapper.CourseMapper;
import com.fax.StudentskaSluzba.mapper.EnrollmentMapper;
import com.fax.StudentskaSluzba.mapper.StudentMapper;
import com.fax.StudentskaSluzba.model.Course;
import com.fax.StudentskaSluzba.model.Enrollment;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.modeldto.EnrollmentDTO;
import com.fax.StudentskaSluzba.service.serviceImpl.EnrollmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/enrollments", produces = MediaType.APPLICATION_JSON_VALUE )
public class EnrollmentController {
    @Autowired
    private EnrollmentServiceImpl enrollmentService;
    @Autowired
    private EnrollmentMapper enrollmentMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMapper courseMapper;

    @RequestMapping(value = "/course/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByCourse(@PathVariable("id") Long courseId){
        try {
            List<EnrollmentDTO> enrollmentDTOS=enrollmentMapper
                    .toEnrollmentDTOList(enrollmentService.getEnrollmentsByCourse(courseId));
            return new ResponseEntity<List<EnrollmentDTO>>(enrollmentDTOS,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/delete",method = RequestMethod.PUT)
    public ResponseEntity<List<EnrollmentDTO>> deleteEnrollmentsByList(@RequestBody List<EnrollmentDTO> enrollmentDTOS){
        List<Enrollment> enrollments=new ArrayList<>();
        for (EnrollmentDTO enrollmentDTO:enrollmentDTOS) {
            Enrollment enrollment=enrollmentService.findOneById(enrollmentDTO.getId());
            if(enrollment==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            enrollment.setDeleted(true);
            enrollments.add(enrollment);
        }
        try {
            enrollments=enrollmentService.saveAll(enrollments);
            return new ResponseEntity<List<EnrollmentDTO>>(enrollmentMapper
                    .toEnrollmentDTOList(enrollments),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<List<EnrollmentDTO>> insertEnrollmentsByList(@RequestBody List<EnrollmentDTO> enrollmentDTOS){
        List<Enrollment> enrollments=new ArrayList<>();
        for (EnrollmentDTO enrollmentDTO :
                enrollmentDTOS) {
            Enrollment enrollment = new Enrollment();
            Student student;
            student=studentMapper.toStudent(enrollmentDTO.getStudent());
            student.setId(enrollmentDTO.getStudent().getId());
            enrollment.setStudent(student);
            Course course=courseMapper.toCourse(enrollmentDTO.getCourse());
            course.setId(enrollmentDTO.getCourse().getId());
            enrollment.setCourse(course);
            enrollments.add(enrollment);
        }
        try {
            enrollments=enrollmentService.saveAll(enrollments);
            return new ResponseEntity<>(enrollmentMapper
                    .toEnrollmentDTOList(enrollments), HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
