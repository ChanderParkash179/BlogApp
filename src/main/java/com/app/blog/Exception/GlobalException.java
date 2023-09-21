package com.app.blog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.blog.Entity.Response;
import com.app.blog.Utils.AppConstants;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice(basePackages = "com.app.blog")
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> resourceNotFoundException(ResourceNotFoundException ex) {
        Response response = new Response();
        response.setResponseCode(AppConstants.NOT_FOUND);
        response.setResponseMessage(ex.getMessage());
        response.setResponseData(null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response> constraintViolationException(ConstraintViolationException ex) {
        Response response = new Response();
        response.setResponseCode(AppConstants.BAD_REQUEST);
        response.setResponseMessage(ex.getMessage());
        response.setResponseData(null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}