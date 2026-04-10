package com.employee.backend.service;

import org.springframework.stereotype.Service;

import com.employee.backend.dto.EmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public String createEmployee(EmployeeDto dto){
        return "Employee created " + dto.name;
    }


}
