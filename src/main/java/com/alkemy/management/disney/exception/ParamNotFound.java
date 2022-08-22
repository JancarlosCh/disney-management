package com.alkemy.management.disney.exception;

//extends RuntimeException -> errores en tiempo de ejecución
public class ParamNotFound extends RuntimeException {
  public ParamNotFound(String error) {
    super(error);
  }
}
