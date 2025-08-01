package com.ashok.example_rbac.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String>handleStudentNotFoundException(StudentNotFoundException studentNotFoundException){
       return new ResponseEntity<>(studentNotFoundException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<String> handleStudentAlreadyExistsException(StudentAlreadyExistsException studentAlreadyExistsException){
        return new ResponseEntity<>(studentAlreadyExistsException.getMessage(),HttpStatus.UNAUTHORIZED);
    }
}
