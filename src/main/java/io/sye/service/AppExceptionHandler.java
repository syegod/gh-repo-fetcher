package io.sye.service;

import io.sye.api.ErrorResponse;
import io.sye.service.github.GithubException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

  @ExceptionHandler(GithubException.class)
  public ResponseEntity<ErrorResponse> handleGithubException(GithubException ex) {
    return new ResponseEntity<>(
        new ErrorResponse(ex.status().value(), ex.getMessage()), ex.status());
  }
}
