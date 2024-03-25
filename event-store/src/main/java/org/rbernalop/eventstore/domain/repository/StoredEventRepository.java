package org.rbernalop.eventstore.domain.repository;

import org.rbernalop.eventstore.domain.StoredEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoredEventRepository extends MongoRepository<StoredEvent, String> {

}
