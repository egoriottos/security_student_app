package com.example.securitytestforiva.dto.queries;

import com.example.securitytestforiva.entities.Groups;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@AllArgsConstructor
@Builder
public class TeacherQuery {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Groups group;
}
