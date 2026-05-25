package com.employee.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.backend.dto.EmployeeDto;
import com.employee.backend.dto.EmployeeProfileUpdateDto;
import com.employee.backend.entity.Employee;
import com.employee.backend.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class UserController {

   @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/me")
    public EmployeeDto getProfile(Authentication auth) {

        String email = auth.getName();

        Employee emp = repository.findByEmailAndIsActiveTrue(email)
        .orElseThrow(() ->new RuntimeException("Employee not found"));

        return toDto(emp);
    }

    @PatchMapping("/me")
    public EmployeeDto updateProfile( Authentication auth,@RequestBody EmployeeProfileUpdateDto request) {
        String email = auth.getName();

        Employee emp = repository.findByEmailAndIsActiveTrue(email)
         .orElseThrow(() ->new RuntimeException("Employee not found"));

        if (request.getName() != null && !request.getName().isBlank()) {
            emp.setName(request.getName());
        }


        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            emp.setPhoneNumber(request.getPhoneNumber());
        }


        if ( request.getPassword() != null && !request.getPassword().isBlank() ) {
            emp.setPassword( passwordEncoder.encode(request.getPassword()));
        }

        Employee saved = repository.save(emp);

        return toDto(saved);
    }

    private EmployeeDto toDto(Employee emp) {

        EmployeeDto dto = new EmployeeDto();

        dto.setId(emp.getId());
        dto.setName(emp.getName());
        dto.setEmail(emp.getEmail());
        dto.setDepartment(emp.getDepartment());
        dto.setPhoneNumber(emp.getPhoneNumber());
        dto.setDod(emp.getDod());
        dto.setSalary(emp.getSalary());

        return dto;
    }
}

