package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.model.Staff;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.model.User;
import com.fax.StudentskaSluzba.repository.StaffRepository;
import com.fax.StudentskaSluzba.repository.UserRepository;
import com.fax.StudentskaSluzba.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Staff> freeStaffByCourse(Long courseID) {
        return staffRepository.fetchStaffFalseCourseLeftJoin(courseID);
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findOneById(Long id) {
        return staffRepository.getOne(id);
    }

    @Override
    public Staff findOneByUser(User user) {
        return staffRepository.findByUser(user);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Transactional
    public Staff staffUserTransaction(User user, Staff staff){
        user=userRepository.save(user);
        staff.setUser(user);
        staff=staffRepository.save(staff);
        return staff;
    }


}
