package com.employee.backend.exception;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.backend.dto.ErrorResponseDto;

import jakarta.validation.ConstraintViolationException;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleNullEntries(MethodArgumentNotValidException ex){
        String errorMessage = "";

        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessage += error.getDefaultMessage() + ", ";
        }
         return new ErrorResponseDto(400, errorMessage, LocalTime.now().toString());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleContraintValidation(ConstraintViolationException ex){
         return new ErrorResponseDto(400, "Validation Error", LocalTime.now().toString());
    }

    @ExceptionHandler(InvalidAgeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleInvalidAge(InvalidAgeException ex){
        return new ErrorResponseDto(400, ex.getMessage(), LocalDateTime.now().toString());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleGenericException(Exception ex) {
        return new ErrorResponseDto(
            500,
            "Something went wrong",
            LocalDateTime.now().toString()
        );
    }
}
