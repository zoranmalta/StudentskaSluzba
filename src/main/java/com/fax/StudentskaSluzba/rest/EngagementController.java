package com.fax.StudentskaSluzba.rest;

import com.fax.StudentskaSluzba.mapper.CourseMapper;
import com.fax.StudentskaSluzba.mapper.EngagementMapper;
import com.fax.StudentskaSluzba.mapper.EnrollmentMapper;
import com.fax.StudentskaSluzba.mapper.StaffMapper;
import com.fax.StudentskaSluzba.model.*;
import com.fax.StudentskaSluzba.modeldto.EngagementDTO;
import com.fax.StudentskaSluzba.modeldto.EnrollmentDTO;
import com.fax.StudentskaSluzba.service.EngagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/engagements", produces = MediaType.APPLICATION_JSON_VALUE )
public class EngagementController {
    @Autowired
    private EngagementService engagementService;
    @Autowired
    private EngagementMapper engagementMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private CourseMapper courseMapper;

    @RequestMapping(value = "/course/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<EngagementDTO>> getEngagementsByCourse(@PathVariable("id") Long id){
        try {
            List<EngagementDTO> list=engagementMapper.toListEngagementDTO(engagementService
                .getEngagementsByCourse(id));
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<List<EngagementDTO>> insertEngagementList(@RequestBody List<EngagementDTO> list){
       List<Engagement> engagementList=new ArrayList<>();
        for (EngagementDTO engagementDTO :
                list) {
            Engagement engagement = new Engagement();
            Staff staff;
            staff=staffMapper.toStaff(engagementDTO.getStaff());
            staff.setId(engagementDTO.getStaff().getId());
            engagement.setStaff(staff);
            Course course=courseMapper.toCourse(engagementDTO.getCourse());
            course.setId(engagementDTO.getCourse().getId());
            engagement.setCourse(course);
            engagementList.add(engagement);
        }

        try {
            engagementList=engagementService.saveAll(engagementList);
            return new ResponseEntity<>(engagementMapper.toListEngagementDTO(engagementList)
                  ,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.PUT)
    public ResponseEntity<List<EngagementDTO>> deleteEngagementByList(@RequestBody List<EngagementDTO> listDTO){
        List<Engagement> engagements=new ArrayList<>();
        for (EngagementDTO engagementDTO:listDTO) {
            Engagement engagement=engagementService.findOneById(engagementDTO.getId());
            if(engagement==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            engagement.setDeleted(true);
            engagements.add(engagement);
        }
        try {
            engagements=engagementService.saveAll(engagements);
            return new ResponseEntity<List<EngagementDTO>>(engagementMapper
                    .toListEngagementDTO(engagements),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
