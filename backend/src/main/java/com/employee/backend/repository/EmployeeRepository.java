package com.employee.backend.repository;

import java.util.Optional;

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

    Page<Employee> findByIsActiveTrueAndNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(String name, String dept,Pageable pageable);

    Optional<Employee>findByEmailAndIsActiveTrue(String email);

}
