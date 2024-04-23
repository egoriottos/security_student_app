package com.example.securitytestforiva.controllers;

import com.example.securitytestforiva.dto.queries.TeacherQuery;
import com.example.securitytestforiva.services.TeacherService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/students/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    private final ModelMapper modelMapper;

    @GetMapping("/All")
    public List<TeacherQuery> getAll() {
       return teacherService.getAllTeachers().stream()
               .map(e->modelMapper.map(e, TeacherQuery.class)).toList();
    }

    @GetMapping("/{name}")
    public TeacherQuery getTeacherByName(@PathVariable String name) {
        return modelMapper.map(teacherService.getTeacherByName(name), TeacherQuery.class);
    }

    @GetMapping("/{surname}")
    public TeacherQuery getTeacherBySurname(@PathVariable String surname) {
        return modelMapper.map(teacherService.getTeacherBySurname(surname), TeacherQuery.class);
    }

    @GetMapping("/{id}")
    public TeacherQuery getTeacherByTeacherId(@PathVariable Integer id) {
        return modelMapper.map(teacherService.getTeacherById(id), TeacherQuery.class);
    }




}
