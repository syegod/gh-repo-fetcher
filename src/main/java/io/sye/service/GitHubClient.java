package io.sye.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sye.api.Branch;
import java.util.Collections;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GitHubClient {

  private final ServiceConfig config;
  private final ObjectMapper objectMapper;

  public GitHubClient(ServiceConfig config, ObjectMapper objectMapper) {
    this.config = config;
    this.objectMapper = objectMapper;
  }

  public Repository[] getRepos(String username) {
    return get("/users/" + username + "/repos", Repository[].class);
  }

  public Branch[] getBranches(String username, String repo) {
    return get("/repos/" + username + "/" + repo + "/branches", Branch[].class);
  }

  // TODO: think about error handling
  private <T> T get(String endpoint, Class<T> valueType) {
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
  }
}
