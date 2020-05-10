package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.Staff;
import com.fax.StudentskaSluzba.modeldto.StaffDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class StaffMapper {

    public Staff toStaff(StaffDTO staffDTO){
        Staff staff= new Staff();
        staff.setFirstName(staffDTO.getFirstName());
        staff.setLastName(staffDTO.getLastName());
        staff.setDeleted(staffDTO.isDeleted());
        staff.setAcademic(staffDTO.getAcademic());
        return staff;
    }

    public StaffDTO toStaffDTO(Staff staff){
        StaffDTO staffDTO=new StaffDTO();
        staffDTO.setId(staff.getId());
        staffDTO.setFirstName(staff.getFirstName());
        staffDTO.setLastName(staff.getLastName());
        staffDTO.setDeleted(staff.isDeleted());
        staffDTO.setAcademic(staff.getAcademic());
        return staffDTO;
    }

    public List<StaffDTO> toListStaffDTO(List<Staff> list){
        return list.stream().map(staff -> toStaffDTO(staff)).collect(Collectors.toList());

    }
}
