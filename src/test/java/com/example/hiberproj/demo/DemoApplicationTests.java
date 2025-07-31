package com.example.hiberproj.demo;

import com.example.hiberproj.demo.models.*;
import com.example.hiberproj.demo.repository.CourseRepository;
import com.example.hiberproj.demo.repository.DepartmentRepository;
import com.example.hiberproj.demo.repository.ProfessorRepository;
import com.example.hiberproj.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Test
	@Transactional
	void testUniveristyModelRelationships(){

//		Department department = Department.builder().name("CS Dept").build();
//		departmentRepository.save(department);
//
//		Professor richard = Professor.builder().firstName("Richard").lastName("Feynman").build();
//
//		Professor turning = Professor.builder().firstName("Alan").lastName("Turning").build();
//
//		ProfessorProfile turningProfile = ProfessorProfile.builder().bio("Professor at CS DEPT").officeNumber("MIT").build();
//		ProfessorProfile alanProfile = ProfessorProfile.builder().bio("Professor at Physics Dept").officeNumber("Cal TECH").build();
//
//		turning.setDepartment(department);
//		turning.setProfessorProfile(turningProfile);
//		professorRepository.save(turning);
//
//		Course course = Course.builder().courseNumber("010").credits(4).professor(turning).build();
//		courseRepository.save(course);

		// find all courses for a specific department
		List<Course> courses = courseRepository.findCoursesInADepartment("Computer Science");
		System.out.println(courses.stream().map(course -> course.getId()).collect(Collectors.toList()));

		// professor who teaches more than N courses.
		List<Professor> professors = professorRepository.findAllProfessorWhoTeachMOreThanNCourses(1);
		System.out.println(professors.stream().map(professor -> professor.getFirstName()).collect(Collectors.toList()));

		//find students enrolled in a class taught by professor.
		List<Student> students = studentRepository.findAllStudentsByProfessor("Alan");
		System.out.println(students.stream().map(student -> student.getId()).collect(Collectors.toList()));

		// find department with maximum number of professors.

		List<Department> departments = departmentRepository.findDepartmentWIthMaxProfessors(PageRequest.of(0,1));
		System.out.println(departments.stream().map(department -> department.getName()).collect(Collectors.toList()));

	}

}
