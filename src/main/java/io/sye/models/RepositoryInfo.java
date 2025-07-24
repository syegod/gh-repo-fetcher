package io.sye.models;

import java.util.List;
import java.util.StringJoiner;

public class RepositoryInfo {

  private String name;

  private String owner;

  private List<Branch> branches;

  public List<Branch> branches() {
    return branches;
  }

  public RepositoryInfo branches(List<Branch> branches) {
    this.branches = branches;
    return this;
  }

  public String owner() {
    return owner;
  }

  public RepositoryInfo owner(String owner) {
    this.owner = owner;
    return this;
  }

  public String name() {
    return name;
  }

  public RepositoryInfo name(String name) {
    this.name = name;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", RepositoryInfo.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("owner='" + owner + "'")
        .add("branches=" + branches)
        .toString();
  }
}
