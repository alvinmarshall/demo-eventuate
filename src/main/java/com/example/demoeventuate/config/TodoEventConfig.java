package com.example.demoeventuate.config;

import com.example.demoeventuate.events.handler.TodoEventHandler;
import com.example.demoeventuate.repository.TodoRepository;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.spring.events.subscriber.TramEventSubscriberConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TramEventSubscriberConfiguration.class})
public class TodoEventConfig {

    @Bean
    public TodoEventHandler todoEventHandler(TodoRepository todoRepository) {
        return new TodoEventHandler(todoRepository);
    }

    @Bean
    public DomainEventDispatcher todoDomainDispatch(DomainEventDispatcherFactory factory, TodoEventHandler eventHandler) {
        return factory.make("TODO_EVENTS", eventHandler.domainEventHandlers());
    }
}
