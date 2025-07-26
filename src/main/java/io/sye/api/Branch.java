package io.sye.api;

import java.util.StringJoiner;

public class Branch {

  private String name;
  private String lastCommitSha;

  public String name() {
    return name;
  }

  public Branch name(String name) {
    this.name = name;
    return this;
  }

  public String lastCommitSha() {
    return lastCommitSha;
  }

  public Branch lastCommitSha(String lastCommitSha) {
    this.lastCommitSha = lastCommitSha;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Branch.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("lastCommitSha='" + lastCommitSha + "'")
        .toString();
  }
}
