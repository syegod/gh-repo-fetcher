package io.sye.service.controllers;

import io.sye.api.ListRepositoriesResponse;
import io.sye.service.services.RepositoryService;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/repositories")
public class RepositoryController {

  private final RepositoryService repositoryService;

  public RepositoryController(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  @GetMapping("/{username}")
  public CompletableFuture<ListRepositoriesResponse> listRepositories(
      @PathVariable("username") String username) {
    return repositoryService.getRepos(username).thenApply(ListRepositoriesResponse::new);
  }
}
