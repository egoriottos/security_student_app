package com.example.securitytestforiva.services;

import com.example.securitytestforiva.entities.Student;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class StudentService {

    private List<Student> students;
    @PostConstruct
    public void loadList(){
        Faker faker = new Faker();
        students = IntStream.rangeClosed(1,50)
                .mapToObj(element->Student.builder()
                        .id(element)
                        .name(faker.app().name())
                        .surname(faker.app().name())
                        .course(faker.random().nextInt(1,6))
                        .build())
                .toList();
    }

    public List<Student> getAll(){
        return students;
    }

    public Student getById(Integer id){
        return  students.stream().filter(student -> student.getId()==id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


    public Student create(Student studentFromCommand){
        students.add(studentFromCommand) ;
        return studentFromCommand;
    }

    public Student update(Integer id, Student updateStudentCommand){
        for(Student student:students){
            if(student.getId()==id){
                student.setName(updateStudentCommand.getName());
                student.setSurname(updateStudentCommand.getSurname());
                student.setCourse(updateStudentCommand.getCourse());
            }
        }
        return students.stream().filter(student -> student.getId()==id).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public void delete(Integer id){
        students.remove(id);
    }
}
