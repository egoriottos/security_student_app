package com.example.securitytestforiva;

import com.example.securitytestforiva.entities.roles.Role;
import com.example.securitytestforiva.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityTestForIvaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityTestForIvaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            RoleRepository repository
    ) {

        return args -> {
            var role = repository.findByRoleName("ROLE_USER");
            if (role.isEmpty()) {
                var newRole = Role.builder()
                        .roleName("ROLE_USER")
                        .build();

                repository.save(newRole);
            }

        };
    }
}
