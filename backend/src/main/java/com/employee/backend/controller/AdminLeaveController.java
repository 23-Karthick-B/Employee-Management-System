package com.employee.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.backend.dto.LeaveResponseDto;
import com.employee.backend.entity.Employee;
import com.employee.backend.entity.EmployeeLeave;
import com.employee.backend.repository.EmployeeLeaveRepository;
import com.employee.backend.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/admin/leave")
public class AdminLeaveController {

   @Autowired
   private EmployeeLeaveRepository leaveRepository;

   @Autowired
   private EmployeeRepository employeeRepository;

   @GetMapping("/pending")
   public List<LeaveResponseDto> getPendingLeaves() {

      List<EmployeeLeave> leaves = leaveRepository.findByStatus("PENDING");

      List<LeaveResponseDto> response =new ArrayList<>();

      for (EmployeeLeave leave : leaves) {

         Employee employee =employeeRepository.findById(leave.getEmployeeId()).orElse(null);

         LeaveResponseDto dto = new LeaveResponseDto();

         dto.setId(leave.getId());
         dto.setEmployeeId( leave.getEmployeeId());
         dto.setLeaveDate( leave.getLeaveDate());
         dto.setReason( leave.getReason());
         dto.setStatus(leave.getStatus());
         dto.setAppliedAt( leave.getAppliedAt());

         if (employee != null) {

               dto.setEmployeeName(employee.getName());
               dto.setDepartment(employee.getDepartment());
         }
         response.add(dto);
      }

      return response;
   }

   @PatchMapping("/{id}/approve")
    public String approveLeave( @PathVariable Integer id) {

        EmployeeLeave leave = leaveRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Leave request not found"));

        leave.setStatus("APPROVED");
        leaveRepository.save(leave);
        return "Leave approved successfully";
    }


    @PatchMapping("/{id}/reject")
    public String rejectLeave(@PathVariable Integer id) {

        EmployeeLeave leave =leaveRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Leave request not found"));

        leave.setStatus("REJECTED");
        leaveRepository.save(leave);
        return "Leave rejected successfully";
    }

}
