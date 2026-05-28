package com.employee.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.backend.entity.EmployeeLeave;

public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave,Integer> {
   List<EmployeeLeave> findByStatus(String status);
   List<EmployeeLeave> findByEmployeeId(Integer employeeId);
}
