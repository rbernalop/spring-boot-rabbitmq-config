package org.rbernalop.eventstore.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.eventstore.domain.StoredEvent;
import org.rbernalop.eventstore.domain.repository.StoredEventRepository;
import org.rbernalop.sharedlib.domain.DomainEvent;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DomainEventStorage {
  private final StoredEventRepository storedEventRepository;
  private final ObjectMapper objectMapper;

  public void store(DomainEvent<?> domainEvent) throws JsonProcessingException {
    String body = objectMapper.writeValueAsString(domainEvent.getBody());
    StoredEvent storedEvent = new StoredEvent(domainEvent.getEventId(), body, domainEvent.getDate());
    storedEventRepository.save(storedEvent);
  }
}
