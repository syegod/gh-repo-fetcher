package io.sye.service.services;

import io.sye.api.BranchInfo;
import io.sye.api.RepositoryInfo;
import io.sye.service.github.GithubClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {

  private final GithubClient gitHubClient;

  public RepositoryService(GithubClient gitHubClient) {
    this.gitHubClient = gitHubClient;
  }

  public CompletableFuture<List<RepositoryInfo>> getRepos(String username) {
    return gitHubClient
        .getRepos(username)
        .thenApply(
            repositories -> Arrays.stream(repositories).filter(repo -> !repo.fork()).toList())
        .thenCompose(
            repositories -> {
              final var futures = new ArrayList<CompletableFuture<RepositoryInfo>>();
              for (var repo : repositories) {
                futures.add(
                    gitHubClient
                        .getBranches(username, repo.name())
                        .thenApply(
                            branches ->
                                new RepositoryInfo(
                                    repo.name(),
                                    repo.owner().login(),
                                    Arrays.stream(branches)
                                        .map(
                                            branch ->
                                                new BranchInfo(
                                                    branch.name(), branch.commit().sha()))
                                        .toList())));
              }
              return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                  .thenApply(v -> futures.stream().map(CompletableFuture::join).toList());
            });
  }
}
