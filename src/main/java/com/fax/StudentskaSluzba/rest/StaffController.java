package com.fax.StudentskaSluzba.rest;


import com.fax.StudentskaSluzba.mapper.StaffMapper;
import com.fax.StudentskaSluzba.model.Authority;
import com.fax.StudentskaSluzba.model.Staff;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.model.User;
import com.fax.StudentskaSluzba.modeldto.StaffDTO;
import com.fax.StudentskaSluzba.modeldto.StudentDTO;
import com.fax.StudentskaSluzba.service.StaffService;
import com.fax.StudentskaSluzba.service.UserService;
import com.fax.StudentskaSluzba.service.serviceImpl.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/staff", produces = MediaType.APPLICATION_JSON_VALUE )
public class StaffController {
    @Autowired
    private StaffServiceImpl staffService;
    @Autowired
    private UserService userService;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    PasswordEncoder  encoder;

    @RequestMapping(value = "all",method = RequestMethod.GET)
    public ResponseEntity<List<StaffDTO>> getAllStaff(){
        try {
            List<Staff> staff=staffService.getAllStaff();
            return new ResponseEntity<>(staffMapper.toListStaffDTO(staff),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/allreverse/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<StaffDTO>> getStaffFreeByCourse(@PathVariable("id") Long id){
        try {
            List<Staff> list=staffService.freeStaffByCourse(id);
            return new ResponseEntity<List<StaffDTO>>(staffMapper.toListStaffDTO(list), HttpStatus.OK);
        }catch (Exception e ){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public ResponseEntity<StaffDTO> getStaffByUser(@PathVariable("id") Long id){
        try {
            User user=userService.findById(id).get();
            Staff staff=staffService.findOneByUser(user);
            return  new ResponseEntity<>(staffMapper.toStaffDTO(staff),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<StaffDTO> insertStaffAndUser(@RequestBody StaffDTO staffDTO){
        System.out.println("doslo sa fronta :" + staffDTO.toString());
        Staff staff=staffMapper.toStaff(staffDTO);
        User user=new User();
        user.setUsername(staffDTO.getUser().getUsername());
        user.setPassword(encoder.encode(staffDTO.getUser().getPassword()));

        Set<Authority> authorities=new HashSet<>();
        Authority authority=new Authority();
        authority.setName("ROLE_PROF");
        authorities.add(authority);
        user.setAuthorities(authorities);

        try {
            //transakcija inserta usera i staff vezano
            staff=staffService.staffUserTransaction(user,staff);
            return new ResponseEntity<>(staffMapper.toStaffDTO(staff),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<StaffDTO> updateStaff(@RequestBody StaffDTO staffDTO) {
        //a student must exist
        Staff staff = staffService.findOneById(staffDTO.getId());
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        staff.setFirstName(staffDTO.getFirstName());
        staff.setLastName(staffDTO.getLastName());
        staff.setAcademic(staffDTO.getAcademic());

        try {
            staff=staffService.save(staff);
            return new ResponseEntity<StaffDTO>(staffMapper.toStaffDTO(staff),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/delete", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<StaffDTO> deleteStaff(@RequestBody StaffDTO staffDTO) {
        //a student must exist
        Staff staff = staffService.findOneById(staffDTO.getId());
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        staff.setDeleted(true);
        staff.getUser().setDeleted(true);

        try {
            staff=staffService.save(staff);
            return new ResponseEntity<StaffDTO>(staffMapper.toStaffDTO(staff),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
