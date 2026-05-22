package com.employee.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.backend.dto.EmployeeLoginRequestDto;
import com.employee.backend.dto.LoginResponseDto;
import com.employee.backend.entity.Employee;
import com.employee.backend.repository.EmployeeRepository;
import com.employee.backend.security.JwtService;


@RestController
@RequestMapping("/employee/auth")
public class LoginController {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody EmployeeLoginRequestDto request) {

        Optional<Employee> emp = repository.findByEmailAndIsActiveTrue(request.getEmail());

        if (emp.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }

        Employee employee = emp.get();

        boolean matches = passwordEncoder.matches(request.getPassword(), employee.getPassword());

        if (!matches) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(employee.getEmail(),"EMPLOYEE");

        return new LoginResponseDto(token);
}
}
