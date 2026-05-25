package com.employee.backend.dto;
import jakarta.validation.constraints.Pattern;

public class EmployeeProfileUpdateDto {

    private String name;

   @Pattern(
      regexp = "\\d{10}",
      message = "Phone number must be exactly 10 digits"
    )
    private String phoneNumber;

    private String password;
    
    public String getName() {
       return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public String getPhoneNumber() {
       return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
       this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
       return password;
    }

    public void setPassword(String password) {
       this.password = password;
    }


    
}
