package io.sye.api;

import java.util.List;
import java.util.StringJoiner;

public class RepositoryInfo {

  private String repositoryName;
  private String ownerLogin;
  private List<Branch> branches;

  public String repositoryName() {
    return repositoryName;
  }

  public RepositoryInfo repositoryName(String repositoryName) {
    this.repositoryName = repositoryName;
    return this;
  }

  public String ownerLogin() {
    return ownerLogin;
  }

  public RepositoryInfo ownerLogin(String ownerLogin) {
    this.ownerLogin = ownerLogin;
    return this;
  }

  public List<Branch> branches() {
    return branches;
  }

  public RepositoryInfo branches(List<Branch> branches) {
    this.branches = branches;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", RepositoryInfo.class.getSimpleName() + "[", "]")
        .add("repositoryName='" + repositoryName + "'")
        .add("ownerLogin='" + ownerLogin + "'")
        .add("branches=" + branches)
        .toString();
  }
}
