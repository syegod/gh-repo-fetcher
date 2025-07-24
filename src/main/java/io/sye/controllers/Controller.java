package io.sye.controllers;


import io.sye.RepoMapper;
import io.sye.models.RepositoryInfo;
import io.sye.services.Service;
import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class Controller {

  private final Service service;

  public Controller(Service service) {
    this.service = service;
  }

  @GetMapping("/{username}")
  public ResponseEntity<List<RepositoryInfo>> getUsersRepo(
      @PathVariable("username") String username) {
    //    if (username == null) {
    //     TODO: handle exceptions
    //    }
    final var infos = service.getRepos(username).stream().map(RepoMapper::toRepoInfo).toList();
    return new ResponseEntity(infos, HttpStatusCode.valueOf(200));
  }
}
