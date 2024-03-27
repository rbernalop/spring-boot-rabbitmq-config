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
public class DomainEventListener implements EventListener<DomainEvent<?>> {
  private final DomainEventStorage domainEventStorage;

  @Override
  public void handle(DomainEvent domainEvent) {
    log.info("Received domain event {} {}", domainEvent.getEventId(), domainEvent.getType());
    try {
      domainEventStorage.store(domainEvent);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
    }
  }
}
