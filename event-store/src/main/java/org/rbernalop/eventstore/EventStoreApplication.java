package org.rbernalop.eventstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"org.rbernalop.*"})
@EnableMongoRepositories(basePackages = "org.rbernalop.*")
public class EventStoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(EventStoreApplication.class, args);
  }

}
