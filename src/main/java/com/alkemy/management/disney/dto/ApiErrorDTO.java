package com.alkemy.management.disney.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

// @Data -> genera getters, toString, equals y hashcode
@Data
// @AllArgsConstructor -> Genera un constructor con todos los parámetros
@AllArgsConstructor
public class ApiErrorDTO {
  private HttpStatus status;
  private String message;
  private List<String> errors;
}
