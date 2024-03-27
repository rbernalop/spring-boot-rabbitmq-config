package org.rbernalop.microservice2.listener;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.sharedlib.infrastructure.EventListener;
import org.rbernalop.sharedlib.infrastructure.events.ProductCreatedEvent;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductCreatedEventListener implements EventListener<ProductCreatedEvent> {
  public void handle(ProductCreatedEvent productCreatedEvent) {
    log.info("Received product created event {}", productCreatedEvent.getEventId());
  }
}
