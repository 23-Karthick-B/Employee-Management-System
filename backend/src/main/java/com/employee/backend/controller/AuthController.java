package com.employee.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.backend.dto.LoginRequestDto;
import com.employee.backend.dto.LoginResponseDto;
import com.employee.backend.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin123";

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody LoginRequestDto request) {

        if (
            request.getUserName().equals(ADMIN_USERNAME)
            &&
            request.getPassword().equals(ADMIN_PASSWORD)
        ) {

            String token =
                jwtService.generateToken(
                    request.getUserName()
                );

            return new LoginResponseDto(token);
        }

        throw new RuntimeException(
            "Invalid username or password"
        );
    }
}