package com.example.hiberproj.demo.repository;

import com.example.hiberproj.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
