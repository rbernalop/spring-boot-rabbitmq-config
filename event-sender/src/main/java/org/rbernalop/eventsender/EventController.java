package org.rbernalop.eventsender;

import lombok.RequiredArgsConstructor;
import org.rbernalop.sharedlib.infrastructure.configuration.RabbitMqEventBus;
import org.rbernalop.sharedlib.infrastructure.events.UserCreatedEvent;
import org.rbernalop.sharedlib.infrastructure.events.UserCreatedEventBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/event")
@RequiredArgsConstructor
public class EventController {

  private final RabbitMqEventBus rabbitMqEventBus;

  @PostMapping
  public void sendUserCreatedEvent(@RequestBody UserCreatedEventBody eventBody) {
    UserCreatedEvent userCreatedEvent = new UserCreatedEvent(eventBody);
    rabbitMqEventBus.publish(userCreatedEvent);
  }
}
