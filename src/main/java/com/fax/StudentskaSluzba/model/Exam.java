package com.fax.StudentskaSluzba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(name = "course_id_period"
        , columnNames = {"course_id"," period"}))
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Course course;

    @Enumerated(EnumType.STRING)
    private ExaminationPeriod period;

    @JsonIgnore
    @OneToMany(targetEntity = ExamRegistration.class, mappedBy = "exam", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<ExamRegistration> examRegistrations = new HashSet<ExamRegistration>();

    @JsonIgnore
    @OneToMany(targetEntity = ExamTest.class, mappedBy = "exam", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<ExamTest> examTests = new HashSet<ExamTest>();

    @Column(name = "exam_start",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp examStart;

    @Column(columnDefinition = "boolean default false")
    private boolean archived;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Exam other = (Exam) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ExaminationPeriod getPeriod() {
        return period;
    }

    public void setPeriod(ExaminationPeriod period) {
        this.period = period;
    }

    public Set<ExamRegistration> getExamRegistrations() {
        return examRegistrations;
    }

    public void setExamRegistrations(Set<ExamRegistration> examRegistrations) {
        this.examRegistrations = examRegistrations;
    }

    public Set<ExamTest> getExamTests() {
        return examTests;
    }

    public void setExamTests(Set<ExamTest> examTests) {
        this.examTests = examTests;
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

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", period=" + period +
                ", examRegistrations=" + examRegistrations +
                ", examStart=" + examStart +
                ", archived=" + archived +
                '}';
    }
}
