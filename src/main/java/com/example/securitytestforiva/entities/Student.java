package com.example.securitytestforiva.entities;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Students {
    private Long id;
    private String name;
    private String surname;
    private int course;
}
