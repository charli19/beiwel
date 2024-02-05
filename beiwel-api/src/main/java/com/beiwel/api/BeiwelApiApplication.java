package com.beiwel.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {"com.beiwel"})
@ComponentScan(basePackages = {"com.beiwel"})
@EntityScan(basePackages = {"com.beiwel"})
@EnableJpaRepositories(basePackages = {"com.beiwel"})
@EnableAutoConfiguration
public class BeiwelApiApplication {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));
    SpringApplication.run(BeiwelApiApplication.class, args);
  }

}