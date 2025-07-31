package com.example.hiberproj.demo.repository;

import com.example.hiberproj.demo.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("select c from Course c join c.professor p join p.department d where d.name = :departmentName")
    List<Course> findCoursesInADepartment(@Param("departmentName") String courseName);
}
