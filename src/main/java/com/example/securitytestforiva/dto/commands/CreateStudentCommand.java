package com.example.securitytestforiva.dto.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateStudentCommand {
    private String name;
    private String surname;
    private Integer course;
}
