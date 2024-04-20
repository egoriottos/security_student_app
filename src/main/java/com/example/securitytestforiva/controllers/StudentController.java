package com.example.securitytestforiva.controllers;


import com.example.securitytestforiva.dto.queries.StudentQuery;
import com.example.securitytestforiva.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students/user")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;
    private final ModelMapper mapper;

    @GetMapping("/allStudents")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<StudentQuery> getStudents(){
        return service.getAll().stream().
                map(element -> mapper.map(element, StudentQuery.class)).
                toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public StudentQuery getById(@PathVariable Integer id){
        return mapper.map(service.getById(id), StudentQuery.class);
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Main page";
    }
}
