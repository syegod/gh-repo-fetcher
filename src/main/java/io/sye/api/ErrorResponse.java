package io.sye.api;

import java.util.StringJoiner;

public class ErrorResponse {

  private int status;
  private String message;

  public ErrorResponse() {}

  public ErrorResponse(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public int status() {
    return status;
  }

  public String message() {
    return message;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ErrorResponse.class.getSimpleName() + "[", "]")
        .add("status=" + status)
        .add("message='" + message + "'")
        .toString();
  }
}
