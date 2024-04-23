package com.example.securitytestforiva.controllers;

import com.example.securitytestforiva.dto.queries.AuthQuery;
import com.example.securitytestforiva.entities.jwt.JwtResponse;
import com.example.securitytestforiva.services.AuthzService;
import com.example.securitytestforiva.services.JwtService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students/auth")
@RequiredArgsConstructor
public class AuthzController {
    private final AuthzService service;
    private final JwtService jwtService;

    @PostMapping("login")
    @PermitAll
    public JwtResponse auth(@RequestBody AuthQuery authQuery){
        var user = service.auth(authQuery.name(), authQuery.password());

        return new JwtResponse(
                jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user)
        );
    }


    @PostMapping("signup")
    @PermitAll
    public JwtResponse signup(@RequestBody AuthQuery authQuery){
        var user = service.signup(authQuery.name(), authQuery.password());
        return new JwtResponse(
                jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user)
        );
    }

    @PostMapping("refresh")
    @PermitAll
    public JwtResponse refresh(@RequestBody String refreshToken){
        final String userName = jwtService.extractUsername(refreshToken);
        var user = service.findUser(userName);
        if(jwtService.isTokenValid(refreshToken,user)){
            return new JwtResponse(
                    jwtService.generateAccessToken(user),
                    jwtService.generateRefreshToken(user)
            );
        }
        throw new RuntimeException("Токен истек");
    }
}
