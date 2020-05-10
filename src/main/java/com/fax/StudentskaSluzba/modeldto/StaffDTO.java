package com.fax.StudentskaSluzba.modeldto;

import com.fax.StudentskaSluzba.model.Academic;
import com.fax.StudentskaSluzba.model.User;

public class StaffDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private boolean deleted;
    private Academic academic;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Academic getAcademic() {
        return academic;
    }

    public void setAcademic(Academic academic) {
        this.academic = academic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "StaffDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", deleted=" + deleted +
                ", academic=" + academic +
                ", user=" + user +
                '}';
    }
}
