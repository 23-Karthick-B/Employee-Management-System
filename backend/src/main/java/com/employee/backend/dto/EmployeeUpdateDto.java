package com.employee.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class EmployeeUpdateDto {
   private String name;

   @Email(message= "Invalid email format")
    @Pattern(
    regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
    message = "Email must be valid"
    )
    private String email;

    private String department;

    @Pattern(
      regexp = "\\d{10}",
      message = "Phone number must be exactly 10 digits"
    )
    private String phoneNumber;

    private LocalDate dod;

    public String getName() {
       return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public String getEmail() {
       return email;
    }

    public void setEmail(String email) {
       this.email = email;
    }

    public String getDepartment() {
       return department;
    }

    public void setDepartment(String department) {
       this.department = department;
    }

    public String getPhoneNumber() {
       return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
       this.phoneNumber = phoneNumber;
    }

    public LocalDate getDod() {
       return dod;
    }

    public void setDod(LocalDate dod) {
       this.dod = dod;
    }


}
