package com.employee.backend.service;


import java.util.ArrayList;
import java.util.List;


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

    private Employee toEntity(EmployeeDto dto){
        Employee emp = new Employee();

        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setDepartment(dto.getDepartment());
        emp.setDod(dto.getDod());
        emp.setPhoneNumber(dto.getPhoneNumber());
        emp.setIsActive(true);

        return emp;
    }

    private EmployeeDto toDto(Employee emp) {
        EmployeeDto dto = new EmployeeDto();

        dto.setId(emp.getId());
        dto.setName(emp.getName());
        dto.setEmail(emp.getEmail());
        dto.setDepartment(emp.getDepartment());
        dto.setPhoneNumber(emp.getPhoneNumber());
        dto.setDod(emp.getDod());
        dto.setIsActive(emp.getIsActive());

        return dto;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto){
        if(repository.existsByEmailIgnoreCase(dto.getEmail())){
            throw new DuplicateEmailException("Employee already exists");
        }

        Employee emp = toEntity(dto);
        repository.save(emp);

        return toDto(emp);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
    List<Employee> getAll = repository.findAll();
    List<EmployeeDto> getAllDtos = new ArrayList<>();

    for (Employee emp : getAll) {
        if (emp.getIsActive()) {
            getAllDtos.add(toDto(emp));
        }
    }
    return getAllDtos;
    }

    @Override
    public EmployeeDto getEmployeeById(Integer id){
        Employee emp = repository.findById(id)
        .filter(Employee::getIsActive)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return toDto(emp);
    }

    @Override
    public EmployeeDto updateEmployee(Integer id, EmployeeDto emp) {
        Employee existing = repository.findById(id)
        .filter(Employee:: getIsActive)
        .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));

        if(repository.existsByEmailIgnoreCaseAndIdNot(emp.getEmail(),id)){
            throw new DuplicateEmailException("Email already exists");
        }
        if(emp.getName() != null && !emp.getName().isBlank()){
            existing.setName(emp.getName());
        }
        
        if(emp.getEmail() != null && !emp.getEmail().isBlank()){
            existing.setEmail(emp.getEmail());
        }

        if(emp.getDepartment()!= null && !emp.getDepartment().isBlank()){
            existing.setDepartment(emp.getDepartment());
        }
        if(emp.getPhoneNumber() != null && !emp.getPhoneNumber().isBlank()){
            existing.setPhoneNumber(emp.getPhoneNumber());
        }
        if(emp.getDod() != null){
            existing.setDod(emp.getDod());
        }
        
        repository.save(existing);

        return toDto(existing);
    }

    @Override
    public String deleteEmployee(Integer id){

        Employee emp = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exits!!"));

        emp.setIsActive(false);
        repository.save(emp);

        return "Employee deleted with id " + id;
    }



    @Override
    public List<EmployeeDto> searchEmployees(String name,String dept){
        List<EmployeeDto> searchList = new ArrayList<>();
        for (Employee emp: repository.findAll()){
            boolean matchName = (name==null || emp.getName().equalsIgnoreCase(name)); // Matching name
            boolean matchDept = (dept == null || emp.getDepartment().equalsIgnoreCase(dept)); // Matching dept
            if (matchName && matchDept && emp.getIsActive()){ // Checking isActive()
                searchList.add(toDto(emp));
            }
        }
        return searchList;
    }

}
