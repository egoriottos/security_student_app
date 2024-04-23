package com.example.securitytestforiva.dto.commands.teachers;

import com.example.securitytestforiva.entities.Groups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class CreateTeacherCommand {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Groups group;
}
