package org.rbernalop.eventstore.infrastructure.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.eventstore.application.service.DomainEventStorage;
import org.rbernalop.sharedlib.domain.DomainEvent;
import org.rbernalop.sharedlib.infrastructure.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DomainEventListener {
  private final DomainEventStorage domainEventStorage;

  @EventListener
  public void handle(DomainEvent<?> domainEvent) throws JsonProcessingException {
    log.info("Received domain event {} {}", domainEvent.getEventId(), domainEvent.getType());
    domainEventStorage.store(domainEvent);
  }
}
