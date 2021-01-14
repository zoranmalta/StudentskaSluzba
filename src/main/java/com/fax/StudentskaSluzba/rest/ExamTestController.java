package com.fax.StudentskaSluzba.rest;

import com.fax.StudentskaSluzba.mapper.ExamMapper;
import com.fax.StudentskaSluzba.mapper.ExamTestMapper;
import com.fax.StudentskaSluzba.mapper.QuestionMapper;
import com.fax.StudentskaSluzba.model.Exam;
import com.fax.StudentskaSluzba.model.ExamTest;
import com.fax.StudentskaSluzba.model.Question;
import com.fax.StudentskaSluzba.modeldto.ExamTestDTO;
import com.fax.StudentskaSluzba.modeldto.QuestionDTO;
import com.fax.StudentskaSluzba.service.ExamService;
import com.fax.StudentskaSluzba.service.ExamTestService;
import com.fax.StudentskaSluzba.service.QuestionService;
import com.fax.StudentskaSluzba.service.serviceImpl.WebSocketSerivce;
import com.fax.StudentskaSluzba.taskschedule.ScheduleExamTestStart;
import com.fax.StudentskaSluzba.taskschedule.ScheduleExamTestStop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/examtest", produces = MediaType.APPLICATION_JSON_VALUE )
public class ExamTestController {
    @Autowired
    private ExamTestMapper examTestMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ExamService examService;
    @Autowired
    private ExamTestService examTestService;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;
    @Autowired
    private WebSocketSerivce webSocketSerivce;

    @RequestMapping(value = "/questions",method = RequestMethod.GET)
    public ResponseEntity<List<QuestionDTO>> getAllByExamTest
            (@RequestParam("examTestId") Long id, @RequestParam(name="page",defaultValue = "0") int page,
             @RequestParam(name="size",defaultValue="5") int size ){
        try {
            ExamTest examTest= examTestService.getOneById(id);
            Sort sort = Sort.by(Sort.Direction.ASC,"id");
            Pageable pageRequest = PageRequest.of(page,size,sort);
            List<Question> questions=questionService.getAllByExam(examTest,pageRequest).toList();
            return new ResponseEntity<>(questionMapper.toListQuestionDTO(questions),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/questions/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<QuestionDTO>> getAllByExamTestId
            (@PathVariable("id") Long id ){
        try {
            ExamTest examTest= examTestService.getOneById(id);
            List<Question> questions=questionService.getAll(examTest);
            return new ResponseEntity<>(questionMapper.toListQuestionDTO(questions),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public ResponseEntity<ExamTestDTO> insertExamTest(@RequestBody ExamTestDTO examTestDTO){
        System.out.println("Stiglo sa fronta : "+examTestDTO);

        try{
        Exam exam=examService.findOneById(examTestDTO.getExam().getId());
        ExamTest examTest=examTestMapper.toExamTest(examTestDTO);
        examTest.setExam(exam);

        examTestDTO=examTestService.saveExamTest(examTest,examTestDTO.getQuestions());

        taskScheduler.schedule(new ScheduleExamTestStart(examTestService,examTestDTO,examTestMapper,webSocketSerivce),examTestDTO.getTestStart());
        //zakazujem zaustavljanje testa tj test je zavrsen 1 minut posle dozvoljenog vremena
        taskScheduler.schedule(new ScheduleExamTestStop(examTestService,examTestDTO,examTestMapper,webSocketSerivce)
                ,new Timestamp(examTestDTO.getTestStart().getTime()+examTestDTO.getTrajanje()*60*1000+60*1000));
            return new ResponseEntity<>(examTestDTO, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "all/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<ExamTestDTO>> getAllByExamId(@PathVariable("id") Long id){
        try {
            Exam exam=examService.findOneById(id);
            List<ExamTest> list=examTestService.getAllByExam(exam);
            return new ResponseEntity<>(examTestMapper.toListExamTestDTO(list),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
