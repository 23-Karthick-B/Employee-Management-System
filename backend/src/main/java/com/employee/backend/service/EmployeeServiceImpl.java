package com.employee.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.employee.backend.dto.EmployeeDto;
import com.employee.backend.exception.DuplicateEmailException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Long,EmployeeDto> mockDb = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto){
        for(EmployeeDto emp : mockDb.values()){
            if(emp.getEmail().equalsIgnoreCase(dto.getEmail())){
                throw new DuplicateEmailException("Employee already exists!!!");            }
        }
        dto.setId(idCounter);
        dto.setActive(true);
        mockDb.put(idCounter, dto);
        idCounter++;
        return dto;
    }

    @Override
    public List<EmployeeDto> getAllEmployee(){
        return new ArrayList<>(mockDb.values());

    }

    @Override
    public EmployeeDto getEmployeeById(Long id){
        EmployeeDto dto = mockDb.get(id);
        if (dto == null || !dto.isActive()){
            throw new RuntimeException("Employee not found!!");
        }
        return dto;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {

        EmployeeDto current = mockDb.get(id);
        if (current == null) {
            throw new RuntimeException("Employee not found !!!");
        }

        for (Map.Entry<Long, EmployeeDto> entry : mockDb.entrySet()) {

            Long keyid = entry.getKey();
            EmployeeDto emp = entry.getValue();

            if (!keyid.equals(id) && emp.getEmail().equalsIgnoreCase(dto.getEmail())) {

                throw new DuplicateEmailException("Email already exists!!!");
            }
        }
        current.setName(dto.getName());
        current.setEmail(dto.getEmail());
        current.setDepartment(dto.getDepartment());

        return current;
    }

    @Override
    public String deleteEmployee(Long id){

        EmployeeDto current = mockDb.get(id);
        if (current == null){
            throw new RuntimeException("Employee Not Found!!!");
        }
        current.setActive(false);

        return "Employee deleted with id " + id;
    }

    @Override
    public List<EmployeeDto> searchEmployees(String name,String dept){
        List<EmployeeDto> searchList = new ArrayList<>();
        for (EmployeeDto emp: mockDb.values()){
            boolean matchName = (name==null || emp.getName().equalsIgnoreCase(name));
            boolean matchDept = (dept == null || emp.getDepartment().equalsIgnoreCase(dept));
            if (matchName && matchDept && emp.isActive()){
                searchList.add(emp);
            }
        }
        return searchList;
    }

}
