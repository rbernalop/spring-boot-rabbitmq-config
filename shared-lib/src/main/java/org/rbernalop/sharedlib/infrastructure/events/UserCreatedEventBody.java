package org.rbernalop.sharedlib.infrastructure.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEventBody {
  private String userId;
  private String name;
  private String surname;
}
