package io.sye;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sye.models.Branch;
import io.sye.models.Repository;
import java.util.Collections;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GitHubClient {

  private final GithubProperties githubProperties;
  private final ObjectMapper objectMapper;

  public GitHubClient(GithubProperties githubProperties, ObjectMapper objectMapper) {
    this.githubProperties = githubProperties;
    this.objectMapper = objectMapper;
  }

  public Repository[] getRepos(String username) {
    try {
      final var res = get("/users/" + username + "/repos");
      return objectMapper.readValue(res, Repository[].class);
    } catch (JsonProcessingException e) {
      // TODO: handle properly
      throw new RuntimeException(e);
    }
  }

  public Branch[] getBranches(String username, String repo) {
    try {
      final var res = get("/repos/" + username + "/" + repo + "/branches");
      return objectMapper.readValue(res, Branch[].class);
    } catch (JsonProcessingException e) {
      // TODO: handle properly
      throw new RuntimeException(e);
    }
  }

  private String get(String url) {
    if (url == null) {
      throw new NullPointerException("URL must not be null");
    }
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.setBearerAuth(githubProperties.getToken());

    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<String> response =
        restTemplate.exchange("https://api.github.com" + url, HttpMethod.GET, entity, String.class);

    return response.getBody();
  }
}
