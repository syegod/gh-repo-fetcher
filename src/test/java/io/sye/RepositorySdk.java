package io.sye;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sye.service.JsonMappers;
import java.util.Collections;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RepositorySdk {

  private final String url;
  private final ObjectMapper objectMapper;

  public RepositorySdk(String url) {
    this.url = url;
    objectMapper = JsonMappers.jsonMapper();
  }

  public ResponseEntity<String> listRepositories(String githubUser) {
    final var restTemplate = new RestTemplate();

    final var headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    final var entity = new HttpEntity<>(headers);

    restTemplate.setErrorHandler(response -> false);

    return restTemplate.exchange(url + "/" + githubUser, HttpMethod.GET, entity, String.class);
  }
}
