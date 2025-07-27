package io.sye.api;

import java.util.List;
import java.util.StringJoiner;

public class RepositoryInfo {

  private String repositoryName;
  private String ownerLogin;
  private List<BranchInfo> branches;

  public RepositoryInfo() {}

  public RepositoryInfo(String repositoryName, String ownerLogin, List<BranchInfo> branches) {
    this.repositoryName = repositoryName;
    this.ownerLogin = ownerLogin;
    this.branches = branches;
  }

  public String repositoryName() {
    return repositoryName;
  }

  public String ownerLogin() {
    return ownerLogin;
  }

  public List<BranchInfo> branches() {
    return branches;
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
