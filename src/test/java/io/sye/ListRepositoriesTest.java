package io.sye;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.sye.api.ErrorResponse;
import io.sye.api.ListRepositoriesResponse;
import io.sye.service.ServiceBootstrap;
import io.sye.service.ServiceConfig;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;

public class ListRepositoriesTest {

  private static ServiceBootstrap serviceBootstrap;
  private static String url;
  private static String githubToken;
  private static ObjectMapper objectMapper;

  @BeforeAll
  static void beforeAll() {
    githubToken = System.getProperty("githubToken");
    if (githubToken == null || githubToken.isEmpty()) {
      throw new IllegalArgumentException("Missing or invalid: githubToken");
    }
    final var appPort = 8080;
    url = "http://localhost:" + appPort + "/api/repositories";
    serviceBootstrap =
        new ServiceBootstrap(new ServiceConfig().appPort(appPort).githubToken(githubToken));
    serviceBootstrap.start();
    objectMapper = jsonMapper();
  }

  @AfterAll
  static void afterAll() {
    if (serviceBootstrap != null) {
      serviceBootstrap.close();
    }
  }

  @Test
  void testListRepositoriesFailedNotFound() throws JsonProcessingException {
    final var repositorySdk = new RepositorySdk(url);
    final var response = repositorySdk.listRepositories(UUID.randomUUID().toString());

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    final var errorResponse = objectMapper.readValue(response.getBody(), ErrorResponse.class);
    assertEquals(404, errorResponse.status(), "errorResponse.status");
    assertFalse(errorResponse.message().isEmpty(), "errorResponse.message.isEmpty");
  }

  @ValueSource(strings = {"octocat", "torvalds", "syegod"})
  @ParameterizedTest
  void testListRepositories(String githubUser) throws JsonProcessingException {
    final var repositorySdk = new RepositorySdk(url);
    final var response = repositorySdk.listRepositories(githubUser);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    final var repositoriesResponse =
        objectMapper.readValue(response.getBody(), ListRepositoriesResponse.class);
    final var repositories = repositoriesResponse.repositories();
    assertFalse(repositories.isEmpty(), "repositories.isEmpty");
    for (var repo : repositories) {
      assertFalse(repo.ownerLogin().isEmpty(), "ownerLogin");
      assertFalse(repo.repositoryName().isEmpty(), "repositoryName");
      final var branches = repo.branches();
      assertFalse(branches.isEmpty(), "branches");
      for (var branch : branches) {
        assertFalse(branch.name().isEmpty(), "branch.name");
        assertFalse(branch.lastCommitSha().isEmpty(), "branch.lastCommitSha");
      }
    }
  }

  private static JsonMapper jsonMapper() {
    return JsonMapper.builder()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
        .configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true)
        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
        .configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, true)
        .configure(JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS, true)
        .serializationInclusion(Include.NON_NULL)
        .visibility(PropertyAccessor.FIELD, Visibility.ANY)
        .addModule(new JavaTimeModule())
        .build();
  }
}
