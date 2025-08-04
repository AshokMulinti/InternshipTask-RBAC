package com.ashok.example_rbac.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String>handleStudentNotFoundException(UserNotFoundException userNotFoundException){
       return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleStudentAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){
        return new ResponseEntity<>(userAlreadyExistsException.getMessage(),HttpStatus.UNAUTHORIZED);
    }
}
