package com.machidior.configuration_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidMethodArgumentException(MethodArgumentNotValidException e){

        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(
            ResourceNotFoundException e,
            WebRequest webRequest
    ){
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                webRequest.getDescription(false),
                "RESOURCE NOT FOUND"
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidEnumException(
            ResourceNotFoundException e,
            WebRequest webRequest
    ){
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                webRequest.getDescription(false),
                "INVALID ENUM PROVIDED"
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceDuplicateException.class)
    public ResponseEntity<ExceptionResponse> handleResourceDuplicateException(
            ResourceDuplicateException e,
            WebRequest webRequest
    ){
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                webRequest.getDescription(false),
                "RESOURCE ALREADY EXISTS"
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}