package com.example.securitytestforiva.controllers;

import com.example.securitytestforiva.dto.commands.group.UpdateGroupCommand;
import com.example.securitytestforiva.dto.queries.GroupQuery;
import com.example.securitytestforiva.entities.Groups;
import com.example.securitytestforiva.services.GroupService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/student/groups")
public class GroupController {
    private final GroupService groupService;
    private final ModelMapper modelMapper;

    @GetMapping("All")
    public List<GroupQuery> getAllGroups() {
        return groupService.getAllGroups().
                stream().map(element->modelMapper.map(element, GroupQuery.class)).
                toList();
    }

    @GetMapping("/{id}")
    public GroupQuery getGroupById(@PathVariable Integer id) {
        return modelMapper.map(groupService.getGroupById(id), GroupQuery.class);
    }

    @GetMapping("/{name}")
    public GroupQuery getGroupByName(@PathVariable String name) {
        return modelMapper.map(groupService.getByName(name), GroupQuery.class);
    }

    @PutMapping("/update/{name}")
    public GroupQuery updateGroup(@PathVariable String name, @RequestBody UpdateGroupCommand group) {
        Groups updatedGroup = groupService.updateGroup(name, modelMapper.map(group, Groups.class));
        return modelMapper.map(updatedGroup, GroupQuery.class);
    }
}
