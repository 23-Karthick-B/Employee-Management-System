package com.employee.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.employee.backend.dto.EmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Long,EmployeeDto> mockDb = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto){
        mockDb.put(idCounter++, dto);
        return dto;
    }

    @Override
    public List<EmployeeDto> getAllEmployee(){
        return new ArrayList<>(mockDb.values());

    }

    @Override
    public EmployeeDto getEmployeeById(Long id){
        EmployeeDto dto = mockDb.get(id);
        if (dto == null){
            throw new RuntimeException("Employee not found!!");
        }
        return dto;
    }

    @Override
    public List<EmployeeDto> getEmployeeByDept(String dept){
        List<EmployeeDto> result = new ArrayList<>();
        for(EmployeeDto emp : mockDb.values()){
            if(emp.getDepartment().equalsIgnoreCase(dept)){
                result.add(emp);
            }
        }
        return result;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto dto){

        EmployeeDto current = mockDb.get(id);
        if (current == null){
            throw new RuntimeException("Employee not found !!!");

        }
        current.setName(dto.getName());
        current.setEmail(dto.getEmail());
        current.setDepartment(dto.getDepartment());
        
        return current;
    }

}
