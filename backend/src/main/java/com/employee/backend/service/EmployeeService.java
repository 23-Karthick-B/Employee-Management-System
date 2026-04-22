package com.employee.backend.service;

import java.util.List;

import com.employee.backend.dto.EmployeeDto;
import com.employee.backend.entity.Employee;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto dto);

    List<Employee> getAllEmployee();
    Employee getEmployeeById(Integer id);
    Employee updateEmployee(Integer id,Employee emp);
    String deleteEmployee(Integer id);
    // List<EmployeeDto> searchEmployees(String name,String dept);

}
