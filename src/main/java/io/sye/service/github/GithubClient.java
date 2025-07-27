package io.sye.service.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sye.service.ServiceConfig;
import io.sye.service.ServiceException;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubClient {

  private final ServiceConfig config;
  private final ObjectMapper objectMapper;

  public GithubClient(ServiceConfig config, ObjectMapper objectMapper) {
    this.config = config;
    this.objectMapper = objectMapper;
  }

  public CompletableFuture<Repository[]> getRepos(String username) {
    return get("/users/" + username + "/repos", Repository[].class);
  }

  public CompletableFuture<Branch[]> getBranches(String username, String repo) {
    return get("/repos/" + username + "/" + repo + "/branches", Branch[].class);
  }

  // TODO: think about error handling
  private <T> CompletableFuture<T> get(String endpoint, Class<T> valueType) {
    return CompletableFuture.supplyAsync(
            () -> {
              final var restTemplate = new RestTemplate();

              HttpHeaders headers = new HttpHeaders();
              headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
              headers.setBearerAuth(config.githubToken());

              final var entity = new HttpEntity<>(headers);
              final var response =
                  restTemplate.exchange(
                      "https://api.github.com" + endpoint, HttpMethod.GET, entity, String.class);
              try {
                return objectMapper.readValue(response.getBody(), valueType);
              } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
              }
            })
        .exceptionally(
            throwable -> {
              if (throwable.getCause() instanceof RestClientResponseException ex
                  && ex.getStatusCode().value() != 401) {
                throw new ServiceException(ex.getStatusCode(), ex.getMessage());
              } else {
                throw new RuntimeException(throwable);
              }
            });
  }
}
