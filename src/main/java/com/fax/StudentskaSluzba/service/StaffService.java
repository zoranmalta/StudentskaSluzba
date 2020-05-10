package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.Staff;
import com.fax.StudentskaSluzba.model.User;

import java.util.List;

public interface StaffService {

    List<Staff> freeStaffByCourse(Long courseID);
    List<Staff> getAllStaff();
    Staff findOneById(Long id);
    Staff findOneByUser(User user);
    Staff save(Staff staff);
}
