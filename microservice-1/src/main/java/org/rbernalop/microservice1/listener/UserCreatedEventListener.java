package org.rbernalop.microservice1.listener;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.sharedlib.infrastructure.EventListener;
import org.rbernalop.sharedlib.infrastructure.events.UserCreatedEvent;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EventListener
public class UserCreatedEventListener {
  public void handle(UserCreatedEvent userCreatedEvent) {
    log.info("Received user created event {}", userCreatedEvent.getEventId());
  }
}
