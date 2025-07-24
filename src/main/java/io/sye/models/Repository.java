package io.sye.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

  private long id;

  private String name;

  private String owner;

  private boolean fork;

  private List<Branch> branches;

  public String name() {
    return name;
  }

  public Repository name(String name) {
    this.name = name;
    return this;
  }

  public String owner() {
    return owner;
  }

  @JsonSetter("owner")
  public Repository unpackOwner(Map<String, Object> owner) {
    this.owner = (String) owner.get("login");
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
