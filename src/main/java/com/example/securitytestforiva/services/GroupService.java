package com.example.securitytestforiva.services;

import com.example.securitytestforiva.entities.Groups;
import com.example.securitytestforiva.repository.GroupRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @PostConstruct
    public void loadGroup() {
        for(int i =1; i<4; i++) {
            Groups group = Groups.builder()
                    .id(i)
                    .name("A89" +i)
                    .students(studentService.createGroupStudents())
                    .teacher(teacherService.loadTeachers())
                    .build();
            groupRepository.save(group);
        }
    }

    public List<Groups> getAllGroups() {
        return groupRepository.findAll();
    }

    public Groups getGroupById(Integer id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Groups getByName(String name) {
            return groupRepository.findByName(name);
    }

    public Groups createGroup(Groups group) {
        group = Groups.builder()
                .name(group.getName())
                .students(studentService.createGroupStudents())
                .build();
        return groupRepository.save(group);
    }

    public void deleteGroup(String name) {
        groupRepository.deleteByName(name);
    }

    public Groups updateGroup(String name, Groups groupFromCommand) {
        try {
            var group=getByName(name);
            Groups savedGroup = null;
            if(!group.getName().equals(groupFromCommand.getName()))
            {
                savedGroup = groupRepository.save(group);
            }
            return savedGroup;
        }
        catch (EntityNotFoundException e){
            System.out.println("Group not found and cant be updated");
            return null;
        }
    }
    @PreDestroy
    public void destroy() {
        groupRepository.deleteAll();
    }
}
