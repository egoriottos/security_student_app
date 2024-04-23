package com.example.securitytestforiva.services;

import com.example.securitytestforiva.entities.Teacher;
import com.example.securitytestforiva.repository.TeacherRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public Teacher loadTeachers(){
        Faker faker = new Faker();
            return Teacher.builder()
                    .name(faker.name().firstName())
                    .surname(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().cellPhone())
                    .build();
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Integer id) {
        try {
            return teacherRepository.findById(id).orElse(null);
        }
        catch (EntityNotFoundException e){
            System.out.println("No teacher found with id " + id);
        }
        return null;
    }

    public void deleteTeacherById(Integer id) {
        try {
            teacherRepository.deleteById(id);
        }
        catch (EntityNotFoundException e){
            System.out.println("No teacher found with id " + id+ " and cannot be deleted");
        }
    }
    public Teacher createTeacher(Teacher teacher) {
        try {
            return teacherRepository.save(teacher);
        }
        catch (EntityExistsException e){
            System.out.println("Teacher with " +"already exists or wrong data");
        }
        return null;
    }

    public Teacher updateTeacher(Integer id,Teacher teacherFromCommand) {
        try {
            var teacher = getTeacherById(id);
            Teacher savedTeacher = null;
            if (!teacher.getEmail().equals(teacherFromCommand.getEmail())
                    || !teacher.getPhone().equals(teacherFromCommand.getPhone())
                    || !teacher.getName().equals(teacherFromCommand.getName())
                    || !teacher.getSurname().equals(teacherFromCommand.getSurname())
                    || !teacher.getGroup().equals(teacherFromCommand.getGroup())) {
                savedTeacher = teacherRepository.save(teacher);
            }
            return savedTeacher;
        }
        catch (Exception e){
            System.out.println("Teacher with id " + id + " cannot be updated");
        }
        return null;
    }

    @PreDestroy
    public void deleteAll(){
        teacherRepository.deleteAll();
    }

    public Teacher getTeacherByName(String name) {
        try {
            return teacherRepository.findTeacherByName(name);
        }
        catch (EntityNotFoundException e) {
            System.out.println("No teacher found with name " + name);
        }
        return null;
    }

    public Teacher getTeacherBySurname(String surname) {
        try {
            return teacherRepository.findTeacherBySurname(surname);
        }
        catch (EntityNotFoundException e) {
            System.out.println("Teacher with name " + surname + " not found");
        }
        return null;
    }

    public void deleteTeacherBySurname(String surname) {
        try {
            teacherRepository.deleteTeacherBySurname(surname);
        }
        catch (EntityNotFoundException e) {
            System.out.println("Teacher with name " + surname + " not found and cannot be deleted");
        }
    }
}
