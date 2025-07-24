package io.sye.services;

import io.sye.GitHubClient;
import io.sye.models.Repository;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;

@org.springframework.stereotype.Service
public class Service {

  private final ExecutorService executorService;
  private final GitHubClient gitHubClient;

  public Service(ExecutorService executorService, GitHubClient gitHubClient) {
    this.executorService = executorService;
    this.gitHubClient = gitHubClient;
  }

  public List<Repository> getRepos(String username) {
    final var reposArray = gitHubClient.getRepos(username);
    final var repos = Stream.of(reposArray).filter(e -> !e.fork()).toList();
    final var futures =
        repos.stream()
            .map(
                repo ->
                    executorService.submit(
                        () -> {
                          var branches = gitHubClient.getBranches(username, repo.name());
                          repo.branches(List.of(branches));
                          return repo;
                        }))
            .toList();

    return futures.stream()
        .map(
            future -> {
              try {
                return future.get();
              } catch (Exception e) {
                throw new RuntimeException("Failed to fetch branches", e);
              }
            })
        .toList();
  }
}
