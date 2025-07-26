package io.sye.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

  private long id;

  private String name;

  private Owner owner;

  private boolean fork;

  private List<Branch> branches;

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

  public List<Branch> branches() {
    return branches;
  }

  public Repository branches(List<Branch> branches) {
    this.branches = branches;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Repository.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("name='" + name + "'")
        .add("owner='" + owner + "'")
        .add("fork=" + fork)
        .add("branches=" + branches)
        .toString();
  }
}
