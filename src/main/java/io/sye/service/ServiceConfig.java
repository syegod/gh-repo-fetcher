package io.sye.service;

import java.util.StringJoiner;

public class ServiceConfig {

  private int appPort;
  private String githubToken;

  public static ServiceConfig fromSystemProperties() {
    final var appPort = Integer.getInteger("appPort");
    final var githubToken = System.getProperty("githubToken");

    return new ServiceConfig().appPort(appPort).githubToken(githubToken);
  }

  public int appPort() {
    return appPort;
  }

  public ServiceConfig appPort(int appPort) {
    this.appPort = appPort;
    return this;
  }

  public String githubToken() {
    return githubToken;
  }

  public ServiceConfig githubToken(String githubToken) {
    this.githubToken = githubToken;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ServiceConfig.class.getSimpleName() + "[", "]")
        .add("appPort=" + appPort)
        .add("githubToken='" + githubToken + "'")
        .toString();
  }
}
