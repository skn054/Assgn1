package com.example.hiberproj.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class ProfessorProfile extends BaseModel {

    private String officeNumber;
    private String bio;

    @OneToOne(mappedBy = "professorProfile")
    private Professor professor;
}
