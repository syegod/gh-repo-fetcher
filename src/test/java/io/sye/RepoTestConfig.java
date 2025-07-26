package io.sye;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.sye.models.Branch;
import io.sye.models.Owner;
import io.sye.models.Repository;
import java.util.List;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class RepoTestConfig {

  @TestConfiguration
  static class TestConfig {
    @Bean
    public GitHubClient gitHubClient() {
      GitHubClient mock = mock(GitHubClient.class);
      when(mock.getRepos(Mockito.anyString()))
          .thenReturn(
              new Repository[] {
                new Repository()
                    .name("test-repo")
                    .branches(List.of(new Branch().name("test-branch").sha("foo123bar321")))
                    .owner(new Owner().login("test-owner"))
              });
      return mock;
    }
  }
}
