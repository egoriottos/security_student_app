package com.example.securitytestforiva.controllers;

import com.example.securitytestforiva.dto.commands.group.CreateGroupCommand;
import com.example.securitytestforiva.dto.commands.student.CreateStudentCommand;
import com.example.securitytestforiva.dto.commands.student.UpdateStudentCommand;
import com.example.securitytestforiva.dto.commands.teachers.CreateTeacherCommand;
import com.example.securitytestforiva.dto.commands.teachers.UpdateTeacherCommand;
import com.example.securitytestforiva.dto.queries.GroupQuery;
import com.example.securitytestforiva.dto.queries.StudentQuery;
import com.example.securitytestforiva.dto.queries.TeacherQuery;
import com.example.securitytestforiva.entities.Groups;
import com.example.securitytestforiva.entities.Student;
import com.example.securitytestforiva.entities.Teacher;
import com.example.securitytestforiva.services.GroupService;
import com.example.securitytestforiva.services.StudentService;
import com.example.securitytestforiva.services.TeacherService;
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
    private final GroupService groupService;
    private final TeacherService teacherService;

    @PutMapping("/update/student/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentQuery update(@PathVariable Integer id, @RequestBody UpdateStudentCommand updateStudentCommand){
        Student studentFromCommand = mapper.map(updateStudentCommand, Student.class);
        Student student = service.update(id, studentFromCommand);
        return mapper.map(student, StudentQuery.class);
    }

    @PostMapping("/create/student")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentQuery create(@RequestBody CreateStudentCommand createStudentCommand){
        Student studentFromCommand = mapper.map(createStudentCommand, Student.class);
        Student student = service.create(studentFromCommand);
        return mapper.map(student, StudentQuery.class);
    }

    @DeleteMapping("/delete/student/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Main page";
    }

    @PostMapping("/newGroup")
    public GroupQuery create(@RequestBody CreateGroupCommand createGroupCommand){
        Groups group = groupService.createGroup(mapper.map(createGroupCommand, Groups.class));
        if(group == null){
            throw new RuntimeException("Группа равна нулю " + null);
        }
        return mapper.map(group, GroupQuery.class);
    }

    @DeleteMapping("/delete/group/{name}")
    public void delete(@PathVariable String name){
        groupService.deleteGroup(name);
    }

    @DeleteMapping("/delete/teacher/{id}")
    public void deleteTeacherById(@PathVariable Integer id) {
        teacherService.deleteTeacherById(id);
    }

    @PostMapping("/createTeacher")
    public TeacherQuery createTeacher(@RequestBody CreateTeacherCommand createTeacherCommand){
        Teacher teacher = teacherService.createTeacher(mapper.map(createTeacherCommand, Teacher.class));
        return mapper.map(teacher, TeacherQuery.class);
    }

    @PutMapping("/update/teacher/{id}")
    public TeacherQuery updateTeacher(@RequestBody UpdateTeacherCommand updateTeacherCommand, @PathVariable Integer id){
        var teacher = teacherService.updateTeacher(id, mapper.map(updateTeacherCommand, Teacher.class));
        return mapper.map(teacher, TeacherQuery.class);
    }

    @DeleteMapping("/delete/teacher/{surname}")
    public void deleteTeacherBySurname(@PathVariable String surname){
        teacherService.deleteTeacherBySurname(surname);
    }
}
