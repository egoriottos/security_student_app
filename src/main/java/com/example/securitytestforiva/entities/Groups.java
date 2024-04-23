package com.example.securitytestforiva.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Student> students;
    @OneToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
}
