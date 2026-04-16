package com.employee.backend.dto;

public class ErrorResponseDto {
    private int status;
    private String message;
    private String timestamp;

    public ErrorResponseDto(int status,String message, String timestamp){
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
