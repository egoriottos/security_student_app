package com.example.securitytestforiva.controllers;

import com.example.securitytestforiva.dto.commands.CreateStudentCommand;
import com.example.securitytestforiva.dto.commands.UpdateStudentCommand;
import com.example.securitytestforiva.dto.queries.StudentQuery;
import com.example.securitytestforiva.entities.Student;
import com.example.securitytestforiva.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students/admin")
@RequiredArgsConstructor
public class AdminController {
    private final StudentService service;
    private final ModelMapper mapper;

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentQuery update(@PathVariable Integer id, @RequestBody UpdateStudentCommand updateStudentCommand){
        Student studentFromCommand = mapper.map(updateStudentCommand, Student.class);
        Student student = service.update(id, studentFromCommand);
        StudentQuery studentQuery = mapper.map(student, StudentQuery.class);
        return studentQuery;
    }

    @PostMapping("/createStudent")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentQuery create(@RequestBody CreateStudentCommand createStudentCommand){
        Student studentFromCommand = mapper.map(createStudentCommand, Student.class);
        Student student = service.create(studentFromCommand);
        StudentQuery studentQuery= mapper.map(student, StudentQuery.class);
        return studentQuery;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Main page";
    }
}
