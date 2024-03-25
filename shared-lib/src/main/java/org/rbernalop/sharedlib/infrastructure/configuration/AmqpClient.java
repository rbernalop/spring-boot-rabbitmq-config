package org.rbernalop.sharedlib.infrastructure.configuration;

import com.rabbitmq.http.client.Client;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AmqpClient {
  private final TopicExchange exchangeToConsumeFrom;
  private final Queue queue;
  private final AmqpAdmin amqpAdmin;

  public List<Binding> addRoutingKey(String[] newBindings) {
    List<Binding> bindings = new ArrayList<>();

    for (String newBinding : newBindings) {
      Binding binding = BindingBuilder.bind(queue).to(exchangeToConsumeFrom).with(newBinding);
      amqpAdmin.declareBinding(binding);
      bindings.add(binding);
    }

    deleteNotPresentBindings(newBindings);

    return bindings;
  }

  private void deleteNotPresentBindings(String[] newBindings) {
    List<Binding> currentBindings = getBindingsForQueue();

    for (Binding binding : currentBindings) {
      String routingKey = binding.getRoutingKey();
      if (!containsBinding(newBindings, routingKey)) {
        amqpAdmin.removeBinding(binding);
      }
    }
  }

  private List<Binding> getBindingsForQueue() {
    try {
      URL url = new URL("http://localhost:15672/api");
      Client rabbitmqManagementApiClient = new Client(url, "guest", "guest");
      return rabbitmqManagementApiClient.getQueueBindings("/", queue.getName()).stream()
          .filter(binding -> exchangeToConsumeFrom.getName().equals(binding.getSource()))
          .map(bindingInfo -> new Binding(
              bindingInfo.getDestination(),
              DestinationType.valueOf(String.valueOf(bindingInfo.getDestinationType())),
              exchangeToConsumeFrom.getName(),
              bindingInfo.getRoutingKey(),
              bindingInfo.getArguments()
          ))
          .toList();
    } catch (MalformedURLException | URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private boolean containsBinding(String[] bindings, String binding) {
    for (String b : bindings) {
      if (b.equals(binding)) {
        return true;
      }
    }
    return false;
  }
}
