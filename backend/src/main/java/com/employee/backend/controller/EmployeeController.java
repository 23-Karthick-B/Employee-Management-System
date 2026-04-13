package com.employee.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.backend.dto.EmployeeDto;
import com.employee.backend.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/create")
    public EmployeeDto create(@RequestBody EmployeeDto dto){
        return service.createEmployee(dto);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployee(){
        return service.getAllEmployee();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        return service.getEmployeeById(id);
    }
    @GetMapping("/department/{dept}")
    public List<EmployeeDto> getEmployeeByDept(@PathVariable String dept){
        return service.getEmployeeByDept(dept);
    }

}
