package com.fax.StudentskaSluzba.mapper;

import com.fax.StudentskaSluzba.model.Course;
import com.fax.StudentskaSluzba.modeldto.CourseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CourseMapper {

    public CourseDTO toCourseDTO(Course course){
        CourseDTO courseDTO= new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDeleted(course.isDeleted());
        courseDTO.setSit(course.getSit());
        return courseDTO;
    }

    public Course toCourse(CourseDTO courseDTO){
        Course course=new Course();
        course.setDeleted(courseDTO.isDeleted());
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setSit(courseDTO.getSit());
        return course;
    }

    public List<CourseDTO> toCourseDTOList(List<Course> list){
        return list.stream().map(course -> toCourseDTO(course))
                .collect(Collectors.toList());
    }
}
