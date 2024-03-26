package org.rbernalop.sharedlib.infrastructure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.sharedlib.application.service.DomainEventService;
import org.rbernalop.sharedlib.domain.DomainEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsumeEventFilter {

  private final DomainEventService domainEventService;
  private final ApplicationContext applicationContext;

  @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
  public void beforeEventListener(DomainEvent<?> domainEvent) throws Throwable {
    log.info("Filter logic executed before event listener method");

    if (domainEventService.hasBeenConsumed(domainEvent.getEventId())) {
      log.warn("Event {} has already been consumed. Stopping further processing.", domainEvent.getEventId());
      return;
    }

    domainEventService.store(domainEvent.getEventId());
    callEventHandler(domainEvent);
  }

  private void callEventHandler(DomainEvent<?> domainEvent) throws InvocationTargetException, IllegalAccessException {
    Map<String, Object> beansWithEventListener = applicationContext.getBeansWithAnnotation(EventListener.class);
    for (Object bean : beansWithEventListener.values()) {
      Method[] methods = ReflectionUtils.getDeclaredMethods(bean.getClass());
      for (Method method : methods) {
        if (handlesEventType(method, domainEvent.getClass())) {
          method.invoke(bean, domainEvent);
          return;
        }
      }
    }

    log.warn("No consumer found for event class {}", domainEvent.getClass().getSimpleName());
  }

  private boolean handlesEventType(Method method, Class<?> eventClass) {
    return method.getParameterTypes().length == 1
        && method.getParameterTypes()[0].isAssignableFrom(eventClass);
  }
}
