package com.fax.StudentskaSluzba.modeldto;

import com.fax.StudentskaSluzba.model.Course;

public class EngagementDTO {

    private Long id;
    private boolean deleted;
    private CourseDTO course;
    private StaffDTO staff;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public StaffDTO getStaff() {
        return staff;
    }

    public void setStaff(StaffDTO staff) {
        this.staff = staff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
