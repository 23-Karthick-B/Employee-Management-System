package com.employee.backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.backend.dto.LeaveRequestDto;
import com.employee.backend.entity.Employee;
import com.employee.backend.entity.EmployeeLeave;
import com.employee.backend.repository.EmployeeLeaveRepository;
import com.employee.backend.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeLeaveController {

   @Autowired
   private EmployeeRepository employeeRepository;

   @Autowired
   private EmployeeLeaveRepository leaveRepository;

   @PostMapping("/leave")
   public String applyLeave(@RequestBody LeaveRequestDto dto,Authentication authentication){

      String email = authentication.getName();
      Employee employee = employeeRepository.findByEmailAndIsActiveTrue(email).orElseThrow();
      EmployeeLeave leave = new EmployeeLeave();

      leave.setEmployeeId(employee.getId());
      leave.setLeaveDate(dto.getLeaveDate());
      leave.setReason(dto.getReason());
      leave.setStatus("PENDING");
      leave.setAppliedAt(LocalDateTime.now());
      leaveRepository.save(leave);
      return "Leave Applied Succesfully!";
   }

   @GetMapping("/my-leaves")
   public List<EmployeeLeave> getMyLeaves(Authentication authentication) {

      String email =authentication.getName();

      Employee employee = employeeRepository.findByEmailAndIsActiveTrue(email).orElseThrow();

      return leaveRepository .findByEmployeeId(employee.getId());
   }
}
