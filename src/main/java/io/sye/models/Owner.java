package io.sye.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

  private String login;

  public String login() {
    return login;
  }

  public Owner login(String login) {
    this.login = login;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Owner.class.getSimpleName() + "[", "]")
        .add("login='" + login + "'")
        .toString();
  }
}
