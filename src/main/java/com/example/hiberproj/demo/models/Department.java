package com.example.hiberproj.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Entity
@Getter
@Setter
public class Department extends BaseModel{

    private String name;
    @OneToMany(mappedBy = "department")
    Set<Professor> professorList;

}
