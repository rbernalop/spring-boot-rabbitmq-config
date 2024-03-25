package org.rbernalop.eventstore.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "domainEvent")
@AllArgsConstructor
@NoArgsConstructor
public class StoredEvent {
  @Id
  private String id;
  private String body;
  private String date;
}
