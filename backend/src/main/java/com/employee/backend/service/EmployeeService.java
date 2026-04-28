package com.employee.backend.service;


import org.springframework.data.domain.Page;

import com.employee.backend.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto dto);

    Page<EmployeeDto> getAllEmployee(int page, int size, String sortBy, String direction);
    EmployeeDto getEmployeeById(Integer id);
    EmployeeDto updateEmployee(Integer id,EmployeeDto emp);
    String deleteEmployee(Integer id);
    Page<EmployeeDto> searchEmployees(String name,String dept,int page,int size);

}
