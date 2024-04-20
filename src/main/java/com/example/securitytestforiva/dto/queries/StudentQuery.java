package com.example.securitytestforiva.dto.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQuery {
    private String name;
    private String surname;
    private Integer course;
}
