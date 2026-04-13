package com.employee.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.employee.backend.dto.EmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Long,EmployeeDto> mockDb = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public String createEmployee(EmployeeDto dto){
        mockDb.put(idCounter++, dto);
        return "Employee created " + dto.name;
    }
    @Override
    public String getAllEmployee(){
        return mockDb.values().toString();

    }

    @Override
    public String getEmployeeById(Long id){
        EmployeeDto dto = mockDb.get(id);
        if (dto == null){
            return "Employee not Found with the id";
        }
        return dto.toString();
    }

}
