package io.sye;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    System.setProperty("github.token", dotenv.get("GITHUB_TOKEN"));
    SpringApplication.run(App.class, args);
  }
}
