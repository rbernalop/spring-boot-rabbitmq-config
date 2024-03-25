# Spring Boot Microservices + RabbitMQ 🍃🐇

Application showing how to configure Spring Boot to apply [event-sourcing](https://martinfowler.com/eaaDev/EventSourcing.html) and thus have an audit of the consumed/produced events. 

![alt text](https://github.com/rbernalop/spring-boot-rabbitmq-config/blob/master/Event%20Sourcing.drawio.png)

## Objectives

- Store all published events data.
- For each microservices, store all consumed events id.
  - This will also help us for the next point.
- ONLY IF WE ARE USING RABBITMQ: Prevent consuming duplicate events.

## Modules

- shared-lib -> Library that every module uses to automatically import:
  - Dependencies of Spring Boot, RabbitMQ, MongoDB...
  - RabbitMQ connection configuration.
  - Filter to check if the event has already been consumed and save its id to the mongo microservices database. 
- event-store -> Microservice that listens for each event and saves all the event information in the event-store Mongo database.
- microservice-1, microservice-2.
