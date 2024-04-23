package com.example.securitytestforiva.services;

import com.example.securitytestforiva.entities.subjects.Subjects;
import com.example.securitytestforiva.repository.SubjectsRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectsRepository subjectsRepository;
    @PostConstruct
    public List<Subjects> loadInDB(){
        Random random = new Random();
        Faker faker = new Faker();
        int randomNumber = random.nextInt(11) + 10;
        List<Subjects> subjects = new ArrayList<>();
        for(int i = 0; i < randomNumber; i++){
            Subjects subject = Subjects.builder()
                    .id(i+1)
                    .name(faker.educator().course())
                    .build();
            subjects.add(subject);
        }
        return subjects;
    }
}
