package com.example.hiberproj.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity

public class Student extends BaseModel{

    private String firstName;
    private String email;

    @ManyToMany
    @JoinTable(name="course_students")
    private Set<Course> courses = new HashSet<>();
}
