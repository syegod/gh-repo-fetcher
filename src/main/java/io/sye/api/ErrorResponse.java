package io.sye.api;

import java.util.StringJoiner;

public class ErrorResponse {

  private int status;
  private String message;

  public int status() {
    return status;
  }

  public ErrorResponse status(int status) {
    this.status = status;
    return this;
  }

  public String message() {
    return message;
  }

  public ErrorResponse message(String message) {
    this.message = message;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ErrorResponse.class.getSimpleName() + "[", "]")
        .add("status=" + status)
        .add("message='" + message + "'")
        .toString();
  }
}
