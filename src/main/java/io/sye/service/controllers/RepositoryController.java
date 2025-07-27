package io.sye.service.controllers;

import io.sye.api.ListRepositoriesResponse;
import io.sye.service.ServiceException;
import io.sye.service.services.RepositoryService;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.HttpStatusCode;
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

  @GetMapping("/{githubUser}")
  public CompletableFuture<ListRepositoriesResponse> listRepositories(
      @PathVariable(value = "githubUser") String githubUser) {
    return repositoryService.getRepos(githubUser).thenApply(ListRepositoriesResponse::new);
  }
}
