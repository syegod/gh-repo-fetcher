package io.sye.service.github;

import org.springframework.http.HttpStatusCode;

public class GithubException extends RuntimeException {

  private HttpStatusCode status;

  public GithubException(HttpStatusCode status, String message) {
    super(message);
    this.status = status;
  }

  public HttpStatusCode status() {
    return status;
  }
}
