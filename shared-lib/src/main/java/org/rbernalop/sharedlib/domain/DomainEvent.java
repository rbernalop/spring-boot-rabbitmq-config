package org.rbernalop.sharedlib.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.Getter;

@Getter
public abstract class DomainEvent<T> {
  private final String eventId;
  private final T body;
  private final String date;

  protected DomainEvent(T body) {
    this.eventId = UUID.randomUUID().toString();
    this.date = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    this.body = body;
  }

  public abstract String getType();
}
