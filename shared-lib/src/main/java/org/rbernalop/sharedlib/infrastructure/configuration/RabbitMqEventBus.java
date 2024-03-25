package org.rbernalop.sharedlib.infrastructure.configuration;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.rbernalop.sharedlib.domain.DomainEvent;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMqEventBus {
  private final AmqpTemplate amqpTemplate;

  public void publish(List<DomainEvent<?>> events) {
    events.forEach(this::publish);
  }

  public void publish(DomainEvent<?> event) {
    amqpTemplate.convertAndSend("amq.topic", event.getType(), event);
  }
}
