package io.sye.service;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class ServiceBootstrap implements AutoCloseable {

  private ConfigurableApplicationContext applicationContext;

  private final ServiceConfig config;

  public ServiceBootstrap(ServiceConfig config) {
    this.config = config;
  }

  public void start() {
    applicationContext =
        new SpringApplicationBuilder(ServiceBootstrap.class)
            .initializers(
                context -> {
                  final var genericAppContext = (GenericApplicationContext) context;
                  genericAppContext.registerBean(ServiceConfig.class, () -> config, bd -> {});
                })
            .web(WebApplicationType.SERVLET)
            .bannerMode(Mode.OFF)
            .properties("server.port=" + config.appPort())
            .run();
  }

  public ApplicationContext applicationContext() {
    return applicationContext;
  }

  public void close() {
    if (applicationContext != null) {
      applicationContext.close();
    }
  }
}
