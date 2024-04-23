package com.example.securitytestforiva.repository;

import com.example.securitytestforiva.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByEmail(String email);
    List<Student> findBySurname(String surname);
    List<Student> findByName(String name);
}
