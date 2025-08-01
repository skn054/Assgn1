package com.example.hiberproj.demo.repository;

import com.example.hiberproj.demo.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {

//    @Query("select p from Professor p join p.courses c group by p having size(c) > :num")
    @Query("select p from Professor p join p.courses c group by p having count(c) > :num")
    List<Professor> findAllProfessorWhoTeachMOreThanNCourses(@Param("num")int num);

    Optional<Professor> findProfessorByFirstNameAndLastName(String firstName, String lastName);
}
