package com.fax.StudentskaSluzba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @OneToOne
    private Academic academic;

    @OneToOne(cascade=CascadeType.REFRESH)
    private User user;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonIgnore
    private Set<Engagement> engagements = new HashSet<Engagement>();

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

    public Set<Engagement> getEngagements() {
        return engagements;
    }

    public void setEngagements(Set<Engagement> engagements) {
        this.engagements = engagements;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", deleted=" + deleted +
                ", academic=" + academic +
                ", user=" + user +
                '}';
    }
}
