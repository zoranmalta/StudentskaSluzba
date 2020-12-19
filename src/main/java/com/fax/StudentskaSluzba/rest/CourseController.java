package com.fax.StudentskaSluzba.rest;

import com.fax.StudentskaSluzba.mapper.CourseMapper;
import com.fax.StudentskaSluzba.model.Course;
import com.fax.StudentskaSluzba.model.Engagement;
import com.fax.StudentskaSluzba.model.Enrollment;
import com.fax.StudentskaSluzba.modeldto.CourseDTO;
import com.fax.StudentskaSluzba.modeldto.StudentDTO;
import com.fax.StudentskaSluzba.service.CourseService;
import com.fax.StudentskaSluzba.service.EngagementService;
import com.fax.StudentskaSluzba.service.EnrollmentService;
import com.fax.StudentskaSluzba.service.serviceImpl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE )
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private EngagementService engagementService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        try {
            return new ResponseEntity<List<CourseDTO>>
                    (courseMapper.toCourseDTOList(courseService.findAll()),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/all/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> getAllCoursesByStudent(@PathVariable("id") Long id){
        try {
            List<Course> courses=courseService.findCoursesByStudentId(id);
            return new ResponseEntity<List<CourseDTO>>(courseMapper.toCourseDTOList(courses)
                    ,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<CourseDTO> insertCourse(@RequestBody CourseDTO courseDTO){
        Course course=courseMapper.toCourse(courseDTO);
        try {
            course=courseService.insertCourse(course);
            return new ResponseEntity<>(courseMapper.toCourseDTO(course),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO){
        Course course=courseService.findOneById(courseDTO.getId());
        if(course==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        course.setName(courseDTO.getName());
        try {
            course=courseService.updateCourse(course);
            return new ResponseEntity<>(courseMapper.toCourseDTO(course),HttpStatus.OK);
        }catch (Exception e ){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/delete", method=RequestMethod.PUT,consumes="application/json")
    public ResponseEntity<CourseDTO> deleteStudent(@RequestBody CourseDTO courseDTO) {
        Course course=courseService.findOneById(courseDTO.getId());
        if(course==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        course.setDeleted(true);
        List<Enrollment> enrollments= enrollmentService.getEnrollmentsByCourse(course.getId());
        if (enrollments.size()!=0){
            for (Enrollment e :
                    enrollments) {
                e.setDeleted(true);
            }
        }
        List<Engagement> engagements=engagementService.getEngagementsByCourse(course.getId());
        if(engagements.size()!=0){
            for (Engagement engagement:engagements) {
                engagement.setDeleted(true);
            }
        }
        try {
            courseService.CourseDeleteTransaction(course,enrollments,engagements);
            return new ResponseEntity<>(courseMapper.toCourseDTO(course),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
