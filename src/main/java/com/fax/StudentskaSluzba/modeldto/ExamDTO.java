package com.fax.StudentskaSluzba.modeldto;

import com.fax.StudentskaSluzba.model.ExaminationPeriod;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ExamDTO {

    private Long id;
    private ExaminationPeriod period;
    private CourseDTO course;
    private Timestamp examStart;
    private boolean archived;
    private List<ExamTestDTO> examTestList=new ArrayList<>();

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



    public List<ExamTestDTO> getExamTestList() {
        return examTestList;
    }

    public void setExamTestList(List<ExamTestDTO> examTestList) {
        this.examTestList = examTestList;
    }

    @Override
    public String toString() {
        return "ExamDTO{" +
                "id=" + id +
                ", period=" + period +
                ", course=" + course +
                ", examStart=" + examStart +
                ", archived=" + archived +
                ", examTestList=" + examTestList +
                '}';
    }
}
