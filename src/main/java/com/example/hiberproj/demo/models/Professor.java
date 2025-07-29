package com.example.hiberproj.demo.models;

import jakarta.persistence.*;

import java.util.Set;

public class Professor extends BaseModel {

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="profile_id")
    private ProfessorProfile professorProfile;

    @OneToMany(mappedBy = "professor")
    Set<Course> courses;

}
