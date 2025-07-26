package io.sye.api;

import java.util.List;

public class ListRepositoriesResponse {

  private List<RepositoryInfo> repositories;

  public List<RepositoryInfo> repositories() {
    return repositories;
  }

  public ListRepositoriesResponse repositories(List<RepositoryInfo> repositories) {
    this.repositories = repositories;
    return this;
  }
}
