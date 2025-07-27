package io.sye.service;

import org.springframework.http.HttpStatusCode;

public class ServiceException extends RuntimeException {

  public HttpStatusCode status;

  public ServiceException(HttpStatusCode status, String message) {
    super(message);
    this.status = status;
  }

  public HttpStatusCode status() {
    return status;
  }
}
