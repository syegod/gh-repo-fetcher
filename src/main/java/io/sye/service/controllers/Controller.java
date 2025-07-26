package io.sye.service.controllers;

import io.sye.api.ErrorResponse;
import io.sye.api.ListRepositoriesResponse;
import io.sye.service.RepoMapper;
import io.sye.service.services.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/repositories")
public class Controller {

  private final Service service;

  public Controller(Service service) {
    this.service = service;
  }

  @GetMapping("/{username}")
  public ResponseEntity<Object> listRepositories(@PathVariable("username") String username) {
    try {
      final var infos = service.getRepos(username).stream().map(RepoMapper::toRepoInfo).toList();
      return new ResponseEntity<>(
          new ListRepositoriesResponse().repositories(infos), HttpStatus.valueOf(200));
    } catch (Exception ex) {
      return new ResponseEntity<>(
          new ErrorResponse().status(500).message(ex.getMessage()), HttpStatus.valueOf(500));
    }
  }
}
