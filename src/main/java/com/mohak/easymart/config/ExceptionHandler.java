package com.mohak.easymart.config;

import com.mohak.easymart.Exceptions.ProductNotFoundException;
import com.mohak.easymart.Utilities.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> HandleProductNotFoundException(ProductNotFoundException productNotFoundException)
    {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(productNotFoundException.getMessage());
        return  new ResponseEntity<>( errorDto, HttpStatus.NOT_FOUND);
    }
}
