package org.rbernalop.sharedlib.domain.repository;

import org.rbernalop.sharedlib.domain.StoredDomainEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DomainEventRepository extends MongoRepository<StoredDomainEvent, String> {

}
