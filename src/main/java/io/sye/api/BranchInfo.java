package io.sye.api;

import java.util.StringJoiner;

public class BranchInfo {

  private String name;
  private String lastCommitSha;

  public BranchInfo() {}

  public BranchInfo(String name, String lastCommitSha) {
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
    return new StringJoiner(", ", BranchInfo.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("lastCommitSha='" + lastCommitSha + "'")
        .toString();
  }
}
