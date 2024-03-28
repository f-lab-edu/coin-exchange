package com.coin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({CustomException.class})

  public ResponseEntity<String> conflictedException(RuntimeException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
  }

  public ResponseEntity<String> notFoundException(CustomException e) { 
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

}
