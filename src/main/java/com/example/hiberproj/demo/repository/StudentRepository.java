package com.example.hiberproj.demo.repository;

import com.example.hiberproj.demo.models.Course;
import com.example.hiberproj.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

//    @Query("select c from courses_students cs join fetch courses c on c.id = cs.courses_id join fetch student s on cs.students_id = s.id")
//    List<Student> findAllCourseEnrolledByStudents();


    // find all students enrolled in a course
    @Query("select s from Student s join s.courses ")
    List<Student> findStudentsEnrolledInAtleastACourse();

    @Query("select s from Student s join fetch s.courses ")
    List<Student> findStudentsEnrolledInAtleastACourseAndGetCourses();

    @Query("select s from Student s left join s.courses c where c is NULL")
    List<Student> findStudentWhoareNotEnrolled();

    // All students enrolled in a course taught by a professor
    @Query("select s from Student s join s.courses c join c.professor p where p.firstName = :name")
    List<Student> findAllStudentsByProfessor(@Param("name") String name);
}
