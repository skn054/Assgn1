package com.example.hiberproj.demo.repository;

import com.example.hiberproj.demo.models.Course;
import com.example.hiberproj.demo.models.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    //find all courses for a specific department


    // find department with maximum professors
    @Query("select p.department from Professor p group by(p.department) order by count(p.department) desc")
    List<Department> findDepartmentWIthMaxProfessors(Pageable pageable);
}
