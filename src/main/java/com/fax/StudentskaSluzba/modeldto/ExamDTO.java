package com.fax.StudentskaSluzba.modeldto;

import com.fax.StudentskaSluzba.model.ExaminationPeriod;

import java.sql.Timestamp;

public class ExamDTO {

    private Long id;
    private ExaminationPeriod period;
    private CourseDTO course;
    private Long courseId;
    private Timestamp examStart;
    private boolean archived;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExaminationPeriod getPeriod() {
        return period;
    }

    public void setPeriod(ExaminationPeriod period) {
        this.period = period;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public Timestamp getExamStart() {
        return examStart;
    }

    public void setExamStart(Timestamp examStart) {
        this.examStart = examStart;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ExamDTO{" +
                "id=" + id +
                ", period=" + period +
                ", courseId=" + courseId +
                ", examStart=" + examStart +
                ", archived=" + archived +
                '}';
    }
}
