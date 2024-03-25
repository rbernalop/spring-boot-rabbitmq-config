package org.rbernalop.sharedlib.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.sharedlib.domain.StoredDomainEvent;
import org.rbernalop.sharedlib.domain.repository.DomainEventRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DomainEventService {
  private final DomainEventRepository domainEventRepository;

  public boolean hasBeenConsumed(String eventId) {
    return domainEventRepository.existsById(eventId);
  }

  public void store(String eventId) {
    StoredDomainEvent domainEvent = new StoredDomainEvent(eventId);
    domainEventRepository.save(domainEvent);
  }
}
