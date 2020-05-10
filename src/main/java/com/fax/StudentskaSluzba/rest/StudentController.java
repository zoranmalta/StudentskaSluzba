package com.fax.StudentskaSluzba.rest;

import com.fax.StudentskaSluzba.mapper.StudentMapper;
import com.fax.StudentskaSluzba.model.Authority;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.model.User;
import com.fax.StudentskaSluzba.modeldto.StudentDTO;
import com.fax.StudentskaSluzba.service.UserService;
import com.fax.StudentskaSluzba.service.serviceImpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/students", produces = MediaType.APPLICATION_JSON_VALUE )
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        List<Student> students=studentService.findAll();
        return new ResponseEntity<List<StudentDTO>>(studentMapper.toListStudentDTO(students)
                ,HttpStatus.OK);
    }

    @RequestMapping(value = "/all/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<StudentDTO>> getAllStudentsByCourse(@PathVariable("id") Long id){
        List<Student> students=studentService.findAllByCourse(id);
        return new ResponseEntity<List<StudentDTO>>(studentMapper.toListStudentDTO(students)
                ,HttpStatus.OK);
    }
    @RequestMapping(value = "/allreverse/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<StudentDTO>> getAllStudentsByCourseNotEnroll(@PathVariable("id") Long id){
        List<Student> students=studentService.freeStudentsByCourse(id);
        return new ResponseEntity<List<StudentDTO>>(studentMapper.toListStudentDTO(students)
                ,HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public ResponseEntity<StudentDTO> getStudentByUser(@PathVariable("id") Long id){
        try {
            User user=userService.findById(id).get();
            Student student=studentService.findByUser(user);
            return  new ResponseEntity<>(studentMapper.toStudentDTO(student),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<StudentDTO> getOneStudent(@PathVariable("id") Long id){
        Student student=studentService.findOneById(id);
        if(student==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<StudentDTO>(studentMapper.toStudentDTO(student),HttpStatus.OK);
    }
    @RequestMapping(value="/index/{cardnumber}", method=RequestMethod.GET)
    public ResponseEntity<StudentDTO> getOneStudent(@PathVariable("cardnumber") String cardNumber){
        Optional<Student> student=studentService.findOneByCardNumber(cardNumber);
        if(student.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<StudentDTO>(studentMapper.toStudentDTO(student.get()),HttpStatus.OK);//
    }
    @PostMapping(path = "/insert")
    public ResponseEntity<StudentDTO> insertStudent(@RequestBody StudentDTO studentDTO){
        System.out.println("doslo sa fronta :" + studentDTO.toString());
        Student student=studentMapper.toStudent(studentDTO);
        User user=new User();
        user.setUsername(studentDTO.getUserForm().getUsername());
        user.setPassword(encoder.encode(studentDTO.getUserForm().getPassword()));

        Set<Authority> authorities=new HashSet<>();
        Authority authority=new Authority();
        authority.setName("ROLE_USER");
        authorities.add(authority);
        user.setAuthorities(authorities);
        try {
            //transakcija inserta usera i studenta vezano
            student= studentService.studentUserTransaction(user,student);
            return new ResponseEntity<StudentDTO>(studentMapper.toStudentDTO(student),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        //a student must exist
        Student student = studentService.findOneById(studentDTO.getId());
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        student.setCardNumber(studentDTO.getCardNumber());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());
        try {
        student=studentService.save(student);
            return new ResponseEntity<StudentDTO>(studentMapper.toStudentDTO(student),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/delete", method=RequestMethod.PUT,consumes="application/json")
    public ResponseEntity<StudentDTO> deleteStudent(@RequestBody StudentDTO studentDTO) {
        //a student must exist
        Student student = studentService.findOneById(studentDTO.getId());
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        student.setDeleted(true);
        student.getUser().setDeleted(true);
        try {
        student=studentService.save(student);
            return new ResponseEntity<StudentDTO>(studentMapper.toStudentDTO(student),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/delete/all", method=RequestMethod.PUT,consumes="application/json")
    public ResponseEntity<List<StudentDTO>> deleteAllStudents(@RequestBody List<StudentDTO> studentDTOList) {
        //a student must exist
        List<Student> students=new ArrayList<>();
        for (StudentDTO studentDTO: studentDTOList) {
        Student student = studentService.findOneById(studentDTO.getId());
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        student.setDeleted(true);
        student.getUser().setDeleted(true);
        students.add(student);
        }
        try {
            students=studentService.saveAll(students);
            return new ResponseEntity<List<StudentDTO>>(studentMapper.toListStudentDTO(students),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
