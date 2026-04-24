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

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNotFound(ResourceNotFoundException ex){
        return new ErrorResponseDto(404, ex.getMessage(), LocalTime.now().toString());
    }

    @ExceptionHandler(DuplicatePhoneNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleDuplicatePhoneNumber(DuplicatePhoneNumberException ex){
        return new ErrorResponseDto(400, ex.getMessage(), LocalTime.now().toString());

    }

}
