package com.employee.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee.backend.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto dto);

    Page<EmployeeDto> getAllEmployee(int page, int size, String sortBy, String direction);
    EmployeeDto getEmployeeById(Integer id);
    EmployeeDto updateEmployee(Integer id,EmployeeDto emp);
    String deleteEmployee(Integer id);
    List<EmployeeDto> searchEmployees(String name,String dept);

}
