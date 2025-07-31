//package com.example.hiberproj.demo;
//
//import com.example.hiberproj.demo.models.*;
//import com.example.hiberproj.demo.repository.CourseRepository;
//import com.example.hiberproj.demo.repository.DepartmentRepository;
//import com.example.hiberproj.demo.repository.ProfessorRepository;
//import com.example.hiberproj.demo.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    @Autowired
//    private DepartmentRepository departmentRepository;
//    @Autowired
//    private ProfessorRepository professorRepository;
//    @Autowired
//    private CourseRepository courseRepository;
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Transactional
//    @Override
//    public void run(String... args) throws Exception {
////        Department department = Department.builder().name("CS Dept").build();
////        departmentRepository.save(department);
////
////        Professor richard = Professor.builder().firstName("Richard").lastName("Feynman").build();
////
////        Professor turning = Professor.builder().firstName("Alan").lastName("Turning").build();
////
////        ProfessorProfile turningProfile = ProfessorProfile.builder().bio("Professor at CS DEPT").officeNumber("MIT").build();
////        ProfessorProfile alanProfile = ProfessorProfile.builder().bio("Professor at Physics Dept").officeNumber("Cal TECH").build();
////
////        turning.setDepartment(department);
////        turning.setProfessorProfile(turningProfile);
////        professorRepository.save(turning);
////
////        Course course = Course.builder().courseNumber("010").credits(4).professor(turning).build();
////        courseRepository.save(course);
////
////
////        Student student = Student.builder().email("skn054@gmail.com").firstName("sk").build();
////
////
////        Student student1 = Student.builder().email("123").firstName("xyz").build();
////        student.setCourses(Set.of(course)); // if we want to save many to many relation then owing entity values must be set.
//////        course.setStudents(Set.of(student));
//////        courseRepository.save(course);
////        student1.setCourses(Set.of(course));
////        studentRepository.save(student);
////        studentRepository.save(student1);
////
////        Optional<Student> optionalStudenti = studentRepository.findById(student.getId());
////        System.out.println(optionalStudenti.get().getFirstName());
//
//        ///  OneToOne is eager fetch type by default
////         Optional<Professor> optionalProfessor =  professorRepository.findById(1L);
////         if(optionalProfessor.isPresent()){
////             System.out.println(optionalProfessor.get().getLastName());
////             System.out.println(optionalProfessor.get().getProfessorProfile().getProfessor().getFirstName());// checks in cache for profile with given id.
////         }
//
//        //ManyToOne is also eager fetch type by default
//        Optional<Professor> optionalProfessor  = professorRepository.findById(1L);
//        if(optionalProfessor.isPresent()){
//            System.out.println(optionalProfessor.get().getDepartment().getName());
//            System.out.println(optionalProfessor.get().getProfessorProfile().getBio());
//        }
//
//        //OneToMany relation are default lazy fetch
//        if(optionalProfessor.isPresent()){
//            //run a query to fetch all courses which are taught by professor
//            //select c1_0.professor_id,c1_0.id,c1_0.course_number,c1_0.created_at,c1_0.credits,c1_0.updated_at from course c1_0 where c1_0.professor_id=?
//            System.out.println(optionalProfessor.get().getCourses());
//        }
//
//        //many to many fetch
//        //N+1 problem.
//        /**
//         * result of the fetch
//         * Hibernate: select s1_0.id,s1_0.created_at,s1_0.email,s1_0.first_name,s1_0.updated_at from student s1_0
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@2f0e7008, com.example.hiberproj.demo.models.Course@75f446df, com.example.hiberproj.demo.models.Course@307e9c02, com.example.hiberproj.demo.models.Course@682270ff]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@8961e55, com.example.hiberproj.demo.models.Course@2f0e7008, com.example.hiberproj.demo.models.Course@682270ff]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@79735611, com.example.hiberproj.demo.models.Course@4bdbfbc6]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@8961e55, com.example.hiberproj.demo.models.Course@2f0e7008, com.example.hiberproj.demo.models.Course@7866fe3e, com.example.hiberproj.demo.models.Course@3a1e5fa7, com.example.hiberproj.demo.models.Course@75f446df, com.example.hiberproj.demo.models.Course@498f1f63, com.example.hiberproj.demo.models.Course@682270ff]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@30cb489a, com.example.hiberproj.demo.models.Course@401d83ce, com.example.hiberproj.demo.models.Course@3a1e5fa7, com.example.hiberproj.demo.models.Course@5466a819]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@8961e55, com.example.hiberproj.demo.models.Course@1c76b2fe, com.example.hiberproj.demo.models.Course@75f446df]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@2aeb3f3b, com.example.hiberproj.demo.models.Course@79735611, com.example.hiberproj.demo.models.Course@307e9c02, com.example.hiberproj.demo.models.Course@4bdbfbc6]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@2f0e7008, com.example.hiberproj.demo.models.Course@7866fe3e, com.example.hiberproj.demo.models.Course@3a1e5fa7, com.example.hiberproj.demo.models.Course@75f446df, com.example.hiberproj.demo.models.Course@4bdbfbc6]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@2f0e7008, com.example.hiberproj.demo.models.Course@3013e1e8, com.example.hiberproj.demo.models.Course@682270ff, com.example.hiberproj.demo.models.Course@13f17b8f]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@401d83ce, com.example.hiberproj.demo.models.Course@3a1e5fa7, com.example.hiberproj.demo.models.Course@75f446df, com.example.hiberproj.demo.models.Course@5466a819]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@2f0e7008, com.example.hiberproj.demo.models.Course@47800bd7]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@2f0e7008, com.example.hiberproj.demo.models.Course@787b7796]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@2f0e7008, com.example.hiberproj.demo.models.Course@113e6e06]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@2f0e7008, com.example.hiberproj.demo.models.Course@1c76b2fe]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@3013e1e8, com.example.hiberproj.demo.models.Course@682270ff]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@30cb489a]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@5058fefb]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@13f17b8f]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@401d83ce]
//         * Hibernate: select c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at from course_students c1_0 join course c1_1 on c1_1.id=c1_0.courses_id where c1_0.students_id=?
//         * [com.example.hiberproj.demo.models.Course@682270ff]
//         *
//         *
//         * */
//        /** N+1 problem*/
////        List<Student> students = studentRepository.findAll();
////        for(Student student: students){
////            System.out.println(student.getCourses());
////        }
//
//        //solution for N+1 problem
////        studentRepository.findAllCourseEnrolledByStudents();
//
//        // find all the students enrolled in a course.
//        List<Student> s = studentRepository.findStudentsEnrolledInAtleastACourse();
//        for(Student s1: s){
//            System.out.println(s1.getId());
//        }
//
//        //find all students enrolled in a course and get list of courses as well eagerly. avoid N+1 problem.
//        /*****
//         *
//         * result of below query.
//         * Hibernate: select s1_0.id,c1_0.students_id,c1_1.id,c1_1.course_number,c1_1.created_at,c1_1.credits,c1_1.professor_id,c1_1.updated_at,s1_0.created_at,s1_0.email,s1_0.first_name,s1_0.updated_at from student s1_0 join course_students c1_0 on s1_0.id=c1_0.students_id join course c1_1 on c1_1.id=c1_0.courses_id
//         * 1
//         * [CS-101, PHY-201, CS-401, ARTH-250]
//         * 2
//         * [CS-101, MATH-301, CS-401]
//         * 4
//         * [CS-101, ECON-101, PSYCH-101, PHY-201, MATH-301, CS-401, BIO-110]
//         * 8
//         * [CS-101, HIST-101, ECON-101, PHY-201, BIO-110]
//         * 9
//         * [CS-101, BUS-300, LING-201, CS-401]
//         * 11
//         * [CS-101, POLSCI-200]
//         * 12
//         * [CS-101, SOC-101]
//         * 13
//         * [CS-101, MUS-150]
//         * 14
//         * [CS-101, ENG-200]
//         * 6
//         * [PHY-201, MATH-301, ENG-200]
//         */
//        //find all students enrolled in a course and get course details.
//        List<Student> s1 = studentRepository.findStudentsEnrolledInAtleastACourseAndGetCourses();
//        for(Student s2:s1){
//            System.out.println(s2.getId());
//            System.out.println(s2.getCourses().stream().map(c-> c.getCourseNumber()).collect(Collectors.toList()));
//        }
//
//
//
//        //find Students who are not enrolled in a course
//        List<Long> studentIds = studentRepository.findStudentWhoareNotEnrolled().stream().map(s2 -> s2.getId()).collect(Collectors.toList());
//        System.out.println(studentIds);
//
//
//
//
//
//
//    }
//}
//
//
//
