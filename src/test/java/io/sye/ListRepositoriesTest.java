package io.sye;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.sye.service.ServiceBootstrap;
import io.sye.service.ServiceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ListRepositoriesTest {

  private static ServiceBootstrap serviceBootstrap;
  private static String url;
  private static String githubToken = System.getProperty("githubToken");

  @BeforeAll
  static void beforeAll() {
    final var appPort = 8080;
    url = "http://localhost:" + appPort + "/api/repositories";
    serviceBootstrap =
        new ServiceBootstrap(new ServiceConfig().appPort(appPort).githubToken(githubToken));
    serviceBootstrap.start();
  }

  @AfterAll
  static void afterAll() {
    if (serviceBootstrap != null) {
      serviceBootstrap.close();
    }
  }

  @Test
  void testListRepositoriesFailed() {
    final var repositorySdk = new RepositorySdk(url);
    final var repositories = repositorySdk.listRepositories(null);

    // TODO: add asserts
    System.out.println(repositories);
  }

  @Test
  void testListRepositories() {
    final var repositorySdk = new RepositorySdk(url);
    final var repositories = repositorySdk.listRepositories("syegod");

    // TODO: add asserts
    assertNotNull(repositories);
    System.out.println(repositories);
  }
}
