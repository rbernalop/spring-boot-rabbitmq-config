package org.rbernalop.sharedlib.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq.template")
public class RabbitMqProperties {
  private String exchange;
  private String routingKey;
  private String defaultReceiveQueue;
}