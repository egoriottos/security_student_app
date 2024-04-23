package com.example.securitytestforiva.repository;

import com.example.securitytestforiva.entities.subjects.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsRepository extends JpaRepository<Subjects, Integer> {
}
