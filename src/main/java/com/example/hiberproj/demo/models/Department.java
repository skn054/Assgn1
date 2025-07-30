package com.example.hiberproj.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Department extends BaseModel{

    private String name;
    @OneToMany(mappedBy = "department")
    Set<Professor> professors = new HashSet<>();

}
