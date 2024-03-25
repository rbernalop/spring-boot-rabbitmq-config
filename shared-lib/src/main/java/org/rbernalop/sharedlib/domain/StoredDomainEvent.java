package org.rbernalop.sharedlib.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "domainEvent")
@AllArgsConstructor
@NoArgsConstructor
public class StoredDomainEvent {
  @Id
  private String id;
}
