package com.employee.backend.exception;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.backend.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleDuplicateEmail(DuplicateEmailException ex){
        return new ErrorResponseDto(
            400, 
            ex.getMessage(),
            LocalTime.now().toString()
        );
    }

}
