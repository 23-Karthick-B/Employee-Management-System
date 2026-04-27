package com.employee.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    boolean existsByEmailIgnoreCaseAndIsActiveTrue(String email);
    boolean existsByEmailIgnoreCaseAndIdNotAndIsActiveTrue(String email, Integer id);
    boolean existsByPhoneNumberAndIsActiveTrue(String phoneNumber);
    boolean existsByPhoneNumberAndIdNotAndIsActiveTrue(String phoneNumber,Integer id);
    Page<Employee> findByIsActiveTrue(Pageable pageable);

}
