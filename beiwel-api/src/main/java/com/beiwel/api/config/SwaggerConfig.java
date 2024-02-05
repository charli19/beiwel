package com.beiwel.api.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Arrays;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "bearer-key", type = SecuritySchemeType.HTTP, scheme = "Bearer", bearerFormat = "JWT")
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi groupedOpenApi() {
    return GroupedOpenApi.builder()
        .group("default")
        .addOpenApiCustomiser(apiCustomiser())
        .build();
  }

  private OpenApiCustomiser apiCustomiser() {
    return openApi -> openApi.info(new Info().title("Beiwel API ")
            .description("Swagger Beiwel")
            .license(new License().name("Beiwel").url("https://beiwel.com"))
            .version("1.0.0"))
        .servers(Arrays.asList(new Server().url("/api")));
  }

}
