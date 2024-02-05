package com.beiwel.database.config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceConfig {

  @Bean
  @Profile("!pro")
  public DataSource dataSourceDev() {
    return DataSourceBuilder.create()
        .driverClassName("com.mysql.cj.jdbc.Driver")
        .url("jdbc:mysql://containers-us-west-56.railway.app:7080/railway")
        .username("root")
        .password("QSLDVSsjATcKgOB3Cz6I")
        .build();
  }

  @Bean
  @Profile("pro")
  public DataSource dataSourcePro() {
    return DataSourceBuilder.create()
        .driverClassName("com.mysql.cj.jdbc.Driver")
        .url("jdbc:mysql://containers-us-west-70.railway.app:7543/railway")
        .username("root")
        .password("Ucv3CA0lYQP9gEgJQvQs")
        .build();
  }
}