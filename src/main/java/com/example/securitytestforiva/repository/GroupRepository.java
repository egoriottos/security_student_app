package com.example.securitytestforiva.repository;

import com.example.securitytestforiva.entities.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Integer> {

    Groups findByName(String name);

    void deleteByName(String name);
}
