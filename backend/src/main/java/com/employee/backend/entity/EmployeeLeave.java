package com.employee.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmployeeLeave")
public class EmployeeLeave {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "Id")
   private Integer id;

   @Column(name = "EmployeeId")
   private Integer employeeId;

   @Column(name = "LeaveDate")
   private LocalDate leaveDate;

   @Column(name = "Reason")
   private String reason;

   @Column(name = "Status")
   private String status;

   @Column(name = "AppliedAt")
   private LocalDateTime appliedAt;

}
