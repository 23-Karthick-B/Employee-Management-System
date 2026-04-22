package com.employee.backend.service;

import java.util.List;

import com.employee.backend.dto.EmployeeDto;
import com.employee.backend.entity.Employee;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto dto);

    List<EmployeeDto> getAllEmployee();
    EmployeeDto getEmployeeById(Integer id);
    EmployeeDto updateEmployee(Integer id,EmployeeDto emp);
    String deleteEmployee(Integer id);
    // List<EmployeeDto> searchEmployees(String name,String dept);

}
