package io.sye.service.github;

import java.util.StringJoiner;

public class Branch {

  private String name;
  private Commit commit;

  public Branch() {}

  public Branch(String name, Commit commit) {
    this.name = name;
    this.commit = commit;
  }

  public String name() {
    return name;
  }

  public Commit commit() {
    return commit;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Branch.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("commit=" + commit)
        .toString();
  }
}
