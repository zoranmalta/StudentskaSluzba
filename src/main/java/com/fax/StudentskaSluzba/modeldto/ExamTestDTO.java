package com.fax.StudentskaSluzba.modeldto;

public class ExamTestDTO {

    private Long id;
    private ExamDTO exam;
    private boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExamDTO getExam() {
        return exam;
    }

    public void setExam(ExamDTO exam) {
        this.exam = exam;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ExamTestDTO{" +
                "id=" + id +
                ", exam=" + exam +
                ", deleted=" + deleted +
                '}';
    }
}
