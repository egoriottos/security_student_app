package com.example.securitytestforiva.controllers;


import com.example.securitytestforiva.dto.queries.StudentQuery;
import com.example.securitytestforiva.services.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students/user")
@AllArgsConstructor
public class StudentController {

    private StudentService service;
    private ModelMapper mapper;



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

    @GetMapping("/{name}")
    public  List<StudentQuery> getStudentsByName(@PathVariable String name){
        return  service.findByName(name).stream()
                .map(element->mapper.map(element, StudentQuery.class))
                .toList();
    }

    @GetMapping("/{surname}")
    public List<StudentQuery> getStudentsBySurname(@PathVariable String surname){
        return service.findBySurname(surname).stream()
                .map(element->mapper.map(element, StudentQuery.class))
                .toList();
    }
    @GetMapping("/{email}")
    public List<StudentQuery>getStudentByCourse(@PathVariable String email){
        return service.findByEmail(email).stream()
                .map(element->mapper.map(element, StudentQuery.class))
                .toList();
    }

}
