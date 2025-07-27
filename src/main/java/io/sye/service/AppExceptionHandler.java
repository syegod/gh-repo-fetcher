package io.sye.service;

import io.sye.api.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<ErrorResponse> handleGithubException(ServiceException ex) {
    return new ResponseEntity<>(
        new ErrorResponse(ex.status().value(), ex.getMessage()), ex.status());
  }
}
