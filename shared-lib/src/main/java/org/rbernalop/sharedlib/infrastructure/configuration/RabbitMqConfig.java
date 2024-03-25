package org.rbernalop.sharedlib.infrastructure.configuration;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RabbitMqConfig {
  private final ConnectionFactory connectionFactory;
  private final RabbitMqProperties rabbitMqProperties;

  @Bean
  public AmqpTemplate amqpTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jacksonConverter());
    return rabbitTemplate;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(jacksonConverter());
    return factory;
  }

  @Bean
  public Queue queue() {
    return QueueBuilder.durable(rabbitMqProperties.getDefaultReceiveQueue()).build();
  }

  @Bean
  public TopicExchange exchangeToConsumeFrom() {
    return new TopicExchange("amq.topic", true, false);
  }

  @Bean
  public List<Binding> exchangeToConsumeFromBindings(@Autowired final AmqpBindingService amqpBindingService) {
    return rabbitMqProperties.getRoutingKey() == null ? new ArrayList<>()
        : amqpBindingService.addRoutingKey(rabbitMqProperties.getRoutingKey().split(","));
  }

  @Bean
  public MessageConverter jacksonConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
