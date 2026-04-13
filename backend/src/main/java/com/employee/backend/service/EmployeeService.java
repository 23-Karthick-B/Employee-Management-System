package com.employee.backend.service;

import com.employee.backend.dto.EmployeeDto;

public interface EmployeeService {
    String createEmployee(EmployeeDto dto);

    String getAllEmployee();
    String getEmployeeById(Long id);

}
