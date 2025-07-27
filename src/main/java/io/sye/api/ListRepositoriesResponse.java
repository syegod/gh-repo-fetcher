package io.sye.api;

import java.util.List;

public class ListRepositoriesResponse {

  private List<RepositoryInfo> repositories;

  public ListRepositoriesResponse() {}

  public ListRepositoriesResponse(List<RepositoryInfo> repositories) {
    this.repositories = repositories;
  }

  public List<RepositoryInfo> repositories() {
    return repositories;
  }
}
