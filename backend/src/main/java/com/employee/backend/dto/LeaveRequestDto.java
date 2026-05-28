package com.employee.backend.dto;

import java.time.LocalDate;

public class LeaveRequestDto {

   private LocalDate leaveDate;

   private String reason;

   public LocalDate getLeaveDate() {
      return leaveDate;
   }

   public void setLeaveDate(LocalDate leaveDate) {
      this.leaveDate = leaveDate;
   }

   public String getReason() {
      return reason;
   }

   public void setReason(String reason) {
      this.reason = reason;
   }

   

}
