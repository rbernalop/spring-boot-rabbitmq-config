package org.rbernalop.sharedlib.infrastructure.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.rbernalop.sharedlib.domain.DomainEvent;

public class UserCreatedEvent extends DomainEvent<UserCreatedEventBody> {
  private static final String TYPE = "user.created";

  @JsonCreator
  public UserCreatedEvent(@JsonProperty("body") UserCreatedEventBody body) {
    super(body);
  }

  @Override
  public String getType() {
    return TYPE;
  }
}
