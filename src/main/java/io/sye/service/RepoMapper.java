package io.sye.service;

import io.sye.api.RepositoryInfo;

public class RepoMapper {

  public static RepositoryInfo toRepoInfo(Repository repo) {
    return new RepositoryInfo()
        .repositoryName(repo.name())
        .ownerLogin(repo.owner().login())
        .branches(repo.branches());
  }
}
