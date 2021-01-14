package com.fax.StudentskaSluzba.rest;

import com.fax.StudentskaSluzba.mapper.AnswerMapper;
import com.fax.StudentskaSluzba.mapper.WorkTestMapper;
import com.fax.StudentskaSluzba.model.Answer;
import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.model.WorkTest;
import com.fax.StudentskaSluzba.modeldto.AnswerDTO;
import com.fax.StudentskaSluzba.modeldto.ExamTestDTO;
import com.fax.StudentskaSluzba.modeldto.QuestionDTO;
import com.fax.StudentskaSluzba.modeldto.WorkTestDTO;
import com.fax.StudentskaSluzba.service.AnswerService;
import com.fax.StudentskaSluzba.service.ExamTestService;
import com.fax.StudentskaSluzba.service.StudentService;
import com.fax.StudentskaSluzba.service.WorkTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/worktest", produces = MediaType.APPLICATION_JSON_VALUE )
public class WorkTestController {
    @Autowired
    private WorkTestService workTestService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private WorkTestMapper workTestMapper;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private ExamTestService examTestService;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/answer/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<AnswerDTO>> getAnswers(@PathVariable(name = "id") Long id){
        try {
            WorkTest workTest=workTestService.getOneById(id);
            if(workTest==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Answer> list=answerService.getAnswersByWorkTest(workTest);
            return new ResponseEntity<>(answerMapper.toListAnswerDTO(list),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/all/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<WorkTestDTO>> getWorkTestsExamTest(@PathVariable(name = "id") Long id){
        try {
            ExamTest examTest=examTestService.getOneById(id);
            List<WorkTest> list=workTestService.getAllByExamTest(examTest);
            return new ResponseEntity<>(workTestMapper.toListWorkTestDTO(list),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/one",method = RequestMethod.GET)
    public ResponseEntity<WorkTestDTO> getWorkTestByExamTestAndStudent
            (@RequestParam("examTestId") Long examTestId,@RequestParam("studentId") Long studentId){
        System.out.println("stiglo za worktest: "+examTestId+" " + studentId);
        try {
            ExamTest examTest=examTestService.getOneById(examTestId);
            Student student=studentService.findOneById(studentId);
            WorkTest workTest=workTestService.getOneByExamTestAndStudent(examTest,student);
            if(workTest==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(workTestMapper.toWorkTestDTO(workTest),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public ResponseEntity<WorkTestDTO> insertWorkTest(@RequestBody WorkTestDTO workTestDTO){
        System.out.println("Stiglo sa fronta : "+workTestDTO);

        try {
            ExamTest examTest=examTestService.getOneById(workTestDTO.getExamTest().getId());
            Student student=studentService.findOneById(workTestDTO.getStudent().getId());
            WorkTest workTest=workTestMapper.toWorkTest(workTestDTO);
            workTest.setExamTest(examTest);
            workTest.setStudent(student);
            workTestDTO=workTestService.insertWorkTest(workTest,workTestDTO.getAnswers());

            return new ResponseEntity<>(workTestDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
