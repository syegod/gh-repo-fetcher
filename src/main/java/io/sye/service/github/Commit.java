package io.sye.service.github;

import java.util.StringJoiner;

public class Commit {

  private String sha;

  public Commit() {}

  public Commit(String sha) {
    this.sha = sha;
  }

  public String sha() {
    return sha;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Commit.class.getSimpleName() + "[", "]")
        .add("sha='" + sha + "'")
        .toString();
  }
}
