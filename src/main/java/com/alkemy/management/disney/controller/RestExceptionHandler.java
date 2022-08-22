package com.alkemy.management.disney.controller;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alkemy.management.disney.dto.ApiErrorDTO;
import com.alkemy.management.disney.exception.ParamNotFound;

@ControllerAdvice
//ResponseEntityExceptionHandler -> para capturar los errores del response
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = { ParamNotFound.class })
  protected ResponseEntity<Object> handleParamNotFound(RuntimeException exception, WebRequest request) {
    
    ApiErrorDTO error = new ApiErrorDTO(
      HttpStatus.BAD_REQUEST,
      exception.getMessage(),
      Arrays.asList("Param Not Found")
    );

    return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
