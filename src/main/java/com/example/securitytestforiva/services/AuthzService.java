package com.example.securitytestforiva.services;

import com.example.securitytestforiva.entities.User;
import com.example.securitytestforiva.repository.RoleRepository;
import com.example.securitytestforiva.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthzService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User auth(String login, String password){
        var user = userRepository.findByName(login).orElseThrow(EntityNotFoundException::new);
        if(passwordEncoder.matches(password, user.getPassword())){
            return user;
        }
        throw new IllegalArgumentException("Неверный логин или пороль");
    }

    public User signup(String login, String password){
        var userFromDB = userRepository.findByName(login);
        if(userFromDB.isPresent()){
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }
        var role = roleRepository.findById(1L).get();

        var user = User.builder()
                .roles(List.of(role))
                .name(login)
                .password(passwordEncoder.encode(password))
                .build();

        return userRepository.save(user);
    }

    public User findUser(String userName){
        return userRepository.findByName(userName).orElseThrow(EntityNotFoundException::new);
    }
}
