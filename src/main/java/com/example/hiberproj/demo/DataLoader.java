package com.example.hiberproj.demo;

import com.example.hiberproj.demo.models.*;
import com.example.hiberproj.demo.repository.CourseRepository;
import com.example.hiberproj.demo.repository.DepartmentRepository;
import com.example.hiberproj.demo.repository.ProfessorRepository;
import com.example.hiberproj.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Department department = Department.builder().name("CS Dept").build();
        departmentRepository.save(department);

        Professor richard = Professor.builder().firstName("Richard").lastName("Feynman").build();

        Professor turning = Professor.builder().firstName("Alan").lastName("Turning").build();

        ProfessorProfile turningProfile = ProfessorProfile.builder().bio("Professor at CS DEPT").officeNumber("MIT").build();
        ProfessorProfile alanProfile = ProfessorProfile.builder().bio("Professor at Physics Dept").officeNumber("Cal TECH").build();

        turning.setDepartment(department);
        turning.setProfessorProfile(turningProfile);
        professorRepository.save(turning);

        Course course = Course.builder().courseNumber("010").credits(4).professor(turning).build();
        courseRepository.save(course);


        Student student = Student.builder().email("skn054@gmail.com").firstName("sk").build();


        Student student1 = Student.builder().email("123").firstName("xyz").courses(new HashSet<>()).build();
        student.setCourses(Set.of(course)); // if we want to save many to many relation then owing entity values must be set.
//        course.setStudents(Set.of(student));
//        courseRepository.save(course);
        studentRepository.save(student);



    }
}
