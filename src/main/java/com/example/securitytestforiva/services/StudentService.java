package com.example.securitytestforiva.services;

import com.example.securitytestforiva.entities.Student;
import com.example.securitytestforiva.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final StudentRepository repository;

    public List<Student> getAll(){
        return repository.findAll();
    }

    public Student getById(Long id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Student create(Student studentFromCommand){
        Student student = repository.save(studentFromCommand);
        return student;
    }

    public Student update(Long id,Student updateStudentCommand){
        var student = repository.getById(id);
        if(!student.getName().equals(updateStudentCommand.getName())
           && !student.getPassword().equals(updateStudentCommand.getPassword()))
        {
            student.setName(updateStudentCommand.getName());
            student.setPassword(updateStudentCommand.getPassword());
        }
        Student save = repository.save(student);
        return save;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
