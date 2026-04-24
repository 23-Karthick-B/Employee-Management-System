package com.employee.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Integer id);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber,Integer id);
    Page<Employee> findByIsActiveTrue(Pageable pageable);

}
