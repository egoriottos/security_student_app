package com.example.securitytestforiva.repository;

import com.example.securitytestforiva.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findTeacherByName(String name);
    Teacher findTeacherBySurname(String surname);
    void deleteTeacherBySurname(String surname);
}
