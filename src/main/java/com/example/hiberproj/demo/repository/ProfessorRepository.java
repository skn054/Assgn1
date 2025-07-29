package com.example.hiberproj.demo.repository;

import com.example.hiberproj.demo.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
