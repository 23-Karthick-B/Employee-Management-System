package com.employee.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.backend.dto.EmployeeDto;
import com.employee.backend.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ems")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // Basic CRUD API's

    @PostMapping("/employees")
    public EmployeeDto create(@Valid @RequestBody EmployeeDto emp){
        return service.createEmployee(emp);
    }

    @GetMapping("/employees")
    public Page<EmployeeDto> getAllEmployee(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "Id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction){
        return service.getAllEmployee(page,size,sortBy,direction);
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Integer id){
        return service.getEmployeeById(id);
    }

    @PatchMapping("/employees/{id}")
    public EmployeeDto updateEmployee(@PathVariable Integer id,@RequestBody EmployeeDto emp){
        return service.updateEmployee(id, emp);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        return service.deleteEmployee(id);
    }

    // Search API's

    @GetMapping("/employees/search")
    public Page<EmployeeDto> searchEmployees(@RequestParam(required = false) String name,@RequestParam(required = false) String dept,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return service.searchEmployees(name, dept,page,size);
    }
}

