package io.sye.service;

import java.util.StringJoiner;

public class Repository {

  private String name;
  private Owner owner;
  private boolean fork;

  public String name() {
    return name;
  }

  public Repository name(String name) {
    this.name = name;
    return this;
  }

  public Owner owner() {
    return owner;
  }

  public Repository owner(Owner owner) {
    this.owner = owner;
    return this;
  }

  public boolean fork() {
    return fork;
  }

  public Repository fork(boolean fork) {
    this.fork = fork;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Repository.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("owner=" + owner)
        .add("fork=" + fork)
        .toString();
  }
}
