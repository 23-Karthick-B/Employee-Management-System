package com.employee.backend.service;

import java.util.List;

import com.employee.backend.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto dto);

    List<EmployeeDto> getAllEmployee();
    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getEmployeeByDept(String dept);
    EmployeeDto updateEmployee(Long id,EmployeeDto dto);

}
