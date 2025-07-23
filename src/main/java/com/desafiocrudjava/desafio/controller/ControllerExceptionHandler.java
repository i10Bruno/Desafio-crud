package com.desafiocrudjava.desafio.controller;

import com.desafiocrudjava.desafio.dto.CustomError;
import com.desafiocrudjava.desafio.dto.ValidationError;
import com.desafiocrudjava.desafio.service.exceptions.DatabaseException;
import com.desafiocrudjava.desafio.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
    HttpStatus status =HttpStatus.NOT_FOUND;
    CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(),request.getRequestURI());
    return ResponseEntity.status(status).body(err);
}



    @ExceptionHandler(DatabaseException.class)

    public  ResponseEntity<CustomError> database (DatabaseException e, HttpServletRequest request){


    HttpStatus status = HttpStatus.BAD_REQUEST;
    CustomError err= new CustomError(Instant.now(), status.value(), e.getMessage(),request.getRequestURI());
    return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(), status.value(),"dados invalidos", request.getRequestURI());

        for(FieldError f :e.getBindingResult().getFieldErrors() ){
            err.addError(f.getField(),f.getDefaultMessage() );
        }

        return ResponseEntity.status(status).body(err);
    }


}
