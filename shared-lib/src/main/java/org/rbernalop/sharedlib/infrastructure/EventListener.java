package org.rbernalop.sharedlib.infrastructure;

import org.rbernalop.sharedlib.domain.DomainEvent;

public interface EventListener<T extends DomainEvent<?>> {
  void handle(T event);
}
