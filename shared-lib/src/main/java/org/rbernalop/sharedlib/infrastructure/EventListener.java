package org.rbernalop.sharedlib.infrastructure;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.lang.annotation.*;

@RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Inherited
public @interface EventListener {
}