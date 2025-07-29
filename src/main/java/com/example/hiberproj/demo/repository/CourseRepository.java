package com.example.hiberproj.demo.repository;

import com.example.hiberproj.demo.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
