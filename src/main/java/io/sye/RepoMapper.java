package io.sye;

import io.sye.models.Repository;
import io.sye.models.RepositoryInfo;

public class RepoMapper {

  public static RepositoryInfo toRepoInfo(Repository repo) {
    return new RepositoryInfo()
        .name(repo.name())
        .owner(repo.owner().login())
        .branches(repo.branches());
  }
}
