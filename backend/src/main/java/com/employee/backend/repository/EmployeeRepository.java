package com.employee.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    boolean existsByEmailIgnoreCaseandIsActiveTrue(String email);
    boolean existsByEmailIgnoreCaseAndIdNotandIsActiveTrue(String email, Integer id);
    boolean existsByPhoneNumberandIsActiveTrue(String phoneNumber);
    boolean existsByPhoneNumberAndIdNotandIsActiveTrue(String phoneNumber,Integer id);
    List<Employee> findByIsActiveTrue();
    
}
