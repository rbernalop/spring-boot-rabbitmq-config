package org.rbernalop.sharedlib.infrastructure.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.rbernalop.sharedlib.domain.DomainEvent;

public class ProductCreatedEvent extends DomainEvent<ProductCreatedEventBody> {
  private static final String TYPE = "product.created";

  @JsonCreator
  public ProductCreatedEvent(@JsonProperty("body") ProductCreatedEventBody body) {
    super(body);
  }

  @Override
  public String getType() {
    return TYPE;
  }
}
