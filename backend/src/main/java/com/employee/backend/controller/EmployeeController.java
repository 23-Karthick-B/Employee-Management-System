package com.employee.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.backend.dto.EmployeeDto;
import com.employee.backend.entity.Employee;
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
    public List<Employee> getAllEmployee(){
        return service.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return service.getEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Integer id,@RequestBody Employee emp){
        return service.updateEmployee(id, emp);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        return service.deleteEmployee(id);
    }

    // Search API's

    // @GetMapping("/employees/search")
    // public List<EmployeeDto> searchEmployees(@RequestParam(required = false) String name,@RequestParam(required = false) String dept){
    //     return service.searchEmployees(name, dept);
    //}
}

