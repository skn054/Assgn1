package com.example.hiberproj.demo.repository;

import com.example.hiberproj.demo.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
