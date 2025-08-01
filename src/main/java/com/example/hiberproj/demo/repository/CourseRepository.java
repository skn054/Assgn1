package com.example.hiberproj.demo.repository;

import com.example.hiberproj.demo.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Collectors;

public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("select c from Course c join c.professor p join p.department d where d.name = :departmentName")
    List<Course> findCoursesInADepartment(@Param("departmentName") String courseName);


    List<Course> findByCreditsGreaterThan(int n);

    @Query("select c from Course c join c.professor p join p.department d where d.name in :deptNames")
    List<Course> findByProfessorOrDepartmentNameIn(@Param("deptNames")List<String> departmentName);
}
