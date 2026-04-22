package com.employee.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.backend.dto.EmployeeDto;
import com.employee.backend.entity.Employee;
import com.employee.backend.exception.DuplicateEmailException;
import com.employee.backend.exception.ResourceNotFoundException;
import com.employee.backend.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;
    private final Map<Long,EmployeeDto> mockDb = new HashMap<>();

    @Override
    public Employee createEmployee(Employee emp){
        if(repository.existsByEmailIgnoreCase(emp.getEmail())){
            throw new DuplicateEmailException("Employee already exists");
        }
        return repository.save(emp);
    }

    @Override
    public List<Employee> getAllEmployee(){
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id){
        return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Integer id, Employee emp) {
        Employee existing = repository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));

        if(repository.existsByEmailIgnoreCaseAndIdNot(existing.getEmail(),id)){
            throw new DuplicateEmailException("Email a;ready exists");
        }
        existing.setName(emp.getName());
        existing.setEmail(emp.getEmail());
        existing.setDepartment(emp.getDepartment());
        existing.setPhoneNumber(emp.getPhoneNumber());
        existing.setDod(emp.getDod());
        
        return repository.save(existing);
    }

    @Override
    public String deleteEmployee(Integer id){

        Employee emp = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exits!!"));

        emp.setIsActive(false);

        return "Employee deleted with id " + id;
    }

    @Override
    public List<EmployeeDto> searchEmployees(String name,String dept){
        List<EmployeeDto> searchList = new ArrayList<>();
        for (EmployeeDto emp: mockDb.values()){
            boolean matchName = (name==null || emp.getName().equalsIgnoreCase(name)); // Matching name
            boolean matchDept = (dept == null || emp.getDepartment().equalsIgnoreCase(dept)); // Matching dept
            if (matchName && matchDept && emp.isActive()){ // Checking isActive()
                searchList.add(emp);
            }
        }
        return searchList;
    }

}
