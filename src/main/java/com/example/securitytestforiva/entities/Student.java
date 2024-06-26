package com.example.securitytestforiva.entities;


import com.example.securitytestforiva.entities.subjects.Subjects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;
    private String name;
    private String surname;
    private String email;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Groups group;
    @OneToMany(mappedBy = "student")
    private List<Subjects> subjects;
}
