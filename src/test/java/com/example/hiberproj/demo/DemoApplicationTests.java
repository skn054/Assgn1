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


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


		//find all departments and eagerly fetch professors and professor profile.
		List<Department> departments1 = departmentRepository.findAllDepartments();
		for(Department department: departments1){
			System.out.println(department.getName());
			System.out.println(department.getProfessors().stream().map(professor  -> professor.getFirstName() + " " + professor.getProfessorProfile().getBio()).collect(Collectors.toList()));
//			System.out.println(department.getProfessors());
		}

		//FInd a professor by first and lastName
		Optional<Professor> professor = professorRepository.findProfessorByFirstNameAndLastName("Richard","Feynman");
		if(professor.isPresent()){
			System.out.println(professor.get().getId());
		}

		//fidn all courses where course credits greater than given number
		List<Course> courses1 = courseRepository.findByCreditsGreaterThan(2);
		System.out.println(courses1.stream().map(course -> course.getCourseNumber()).collect(Collectors.toList()));

		//find all students whose email ends with specific domain
		List<Student> stdlist = studentRepository.findByEmailEndingWith("@example.com");

		//find all courses taught by any professor from given department
		List<Course > courses2 = courseRepository.findByProfessorOrDepartmentNameIn(List.of("Computer Science", "Physics"));
		System.out.println(courses2.stream().map(course -> course.getId()).collect(Collectors.toList()));

	}

}
