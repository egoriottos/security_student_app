package com.example.securitytestforiva.services;

import com.example.securitytestforiva.entities.Groups;
import com.example.securitytestforiva.entities.Student;
import com.example.securitytestforiva.repository.StudentRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final GroupService groupService;

    public List<Student> getAll(){
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Student::getSurname))
                .collect(Collectors.toList());
    }

    public Student getById(Integer id){
        return  repository.findById(id).orElse(null);
    }


    public Student create(Student studentFromCommand){
        Student student = repository.save(studentFromCommand);
        return student;
    }


    public Student update(Integer id, Student updateStudentCommand){
        var student = getById(id);
        Student savedStudent = null;
        if(!student.getName().equals(updateStudentCommand.getName())
            || !student.getSurname().equals(updateStudentCommand.getSurname())
            || !student.getEmail().equals(updateStudentCommand.getEmail())
            || !student.getGroup().equals(updateStudentCommand.getGroup())
        )
        {
            savedStudent = repository.save(student);
        }
        return savedStudent;
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }

    public List<Student> findByEmail(String email){
       return repository.findByEmail(email);
    }

    public List<Student> findBySurname(String surname){
        return repository.findBySurname(surname);
    }

    public List<Student> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Student> createGroupStudents(){
        Faker faker = new Faker();
        Random random = new Random();
        int randomNumber = random.nextInt(11) + 20;
        List<Student> students = IntStream.range(0,randomNumber)
                .mapToObj(i->generateRandomStudent())
                .toList();

        List<Student> savedStudents = students.stream()
                .map(repository::save)
                .collect(Collectors.toList());
        return savedStudents;
    }

    public Student generateRandomStudent(){
        Faker faker = new Faker();
        Student student = Student.builder()
                .name(faker.name().firstName())
                .surname(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();
        return student;
    }

    @PreDestroy
    public void deleteALL(){
        repository.deleteAll();
    }

}
