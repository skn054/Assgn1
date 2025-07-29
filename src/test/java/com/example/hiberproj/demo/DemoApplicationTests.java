package com.example.hiberproj.demo;

import com.example.hiberproj.demo.models.Course;
import com.example.hiberproj.demo.models.Department;
import com.example.hiberproj.demo.models.Professor;
import com.example.hiberproj.demo.models.ProfessorProfile;
import com.example.hiberproj.demo.repository.CourseRepository;
import com.example.hiberproj.demo.repository.DepartmentRepository;
import com.example.hiberproj.demo.repository.ProfessorRepository;
import com.example.hiberproj.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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



	}

}
