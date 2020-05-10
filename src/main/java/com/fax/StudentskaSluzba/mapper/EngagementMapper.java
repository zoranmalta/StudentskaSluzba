package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.Engagement;
import com.fax.StudentskaSluzba.modeldto.EngagementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class EngagementMapper {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StaffMapper staffMapper;

    public Engagement toEngagement(EngagementDTO engagementDTO){
        Engagement engagement=new Engagement();
        engagement.setId(engagementDTO.getId());
        engagement.setDeleted(engagementDTO.isDeleted());
        engagement.setCourse(courseMapper.toCourse(engagementDTO.getCourse()));
        engagement.setStaff(staffMapper.toStaff(engagementDTO.getStaff()));
        return engagement;
    }
    public EngagementDTO toEngagementDTO(Engagement engagement){
        EngagementDTO engagementDTO=new EngagementDTO();
        engagementDTO.setId(engagement.getId());
        engagementDTO.setDeleted(engagement.isDeleted());
        engagementDTO.setCourse(courseMapper.toCourseDTO(engagement.getCourse()));
        engagementDTO.setStaff(staffMapper.toStaffDTO(engagement.getStaff()));
        return engagementDTO;
    }

    public List<EngagementDTO> toListEngagementDTO(List<Engagement> list){
        return list.stream().map(engagement -> toEngagementDTO(engagement))
                .collect(Collectors.toList());
    }

    public List<Engagement> toListEngagement(List<EngagementDTO> list){
        return list.stream().map(engagementDTO -> toEngagement(engagementDTO))
                .collect(Collectors.toList());
    }
}
