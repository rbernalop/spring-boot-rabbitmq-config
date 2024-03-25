package org.rbernalop.sharedlib.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.rbernalop.sharedlib.application.service.DomainEventService;
import org.rbernalop.sharedlib.domain.DomainEvent;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class EventListenerAspect {

  private final DomainEventService domainEventService;

  @Around("@annotation(org.rbernalop.sharedlib.infrastructure.EventListener)")
  public Object beforeEventListener(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("Filter logic executed before event listener method");
    DomainEvent<?> domainEvent = getDomainEvent(joinPoint.getArgs());

    if (domainEvent != null && !domainEventService.hasBeenConsumed(domainEvent.getEventId())) {
      domainEventService.store(domainEvent.getEventId());
      return joinPoint.proceed();
    }

    log.warn("Event {} has already been consumed. Stopping further processing.", domainEvent != null ? domainEvent.getEventId() : null);
    return null;
  }

  private DomainEvent<?> getDomainEvent(Object[] args) {
    if(args.length == 1 && args[0] instanceof DomainEvent<?>) {
      return  (DomainEvent<?>) args[0];
    }
    return null;
  }
}
