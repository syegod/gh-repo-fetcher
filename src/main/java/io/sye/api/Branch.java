package io.sye.api;

import java.util.StringJoiner;

public class Branch {

  private String name;
  private String lastCommitSha;

  public Branch() {}

  public Branch(String name, String lastCommitSha) {
    this.name = name;
    this.lastCommitSha = lastCommitSha;
  }

  public String name() {
    return name;
  }

  public String lastCommitSha() {
    return lastCommitSha;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Branch.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("lastCommitSha='" + lastCommitSha + "'")
        .toString();
  }
}
