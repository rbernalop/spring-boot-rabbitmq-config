package org.rbernalop.microservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"org.rbernalop.*"})
@EnableMongoRepositories(basePackages = "org.rbernalop.*")
public class Microservice2Application {

  public static void main(String[] args) {
    SpringApplication.run(Microservice2Application.class, args);
  }

}
