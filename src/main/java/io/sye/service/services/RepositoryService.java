package io.sye.service.services;

import io.sye.api.RepositoryInfo;
import io.sye.service.github.GitHubClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {

  private final GitHubClient gitHubClient;

  public RepositoryService(GitHubClient gitHubClient) {
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
                                    repo.name(), repo.owner().login(), List.of(branches))));
              }
              return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                  .thenApply(v -> futures.stream().map(CompletableFuture::join).toList());
            });
  }
}
