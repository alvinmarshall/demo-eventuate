package com.example.demoeventuate.events.handler;

import com.example.demoeventuate.events.TodoCreated;
import com.example.demoeventuate.model.Todo;
import com.example.demoeventuate.repository.TodoRepository;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class TodoEventHandler {
    private final TodoRepository todoRepository;

    public TodoEventHandler(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
                .forAggregateType(Todo.class.getName())
                .onEvent(TodoCreated.class, eventEnvelope -> {
                    TodoCreated event = eventEnvelope.getEvent();
                    Todo todo = Todo.builder().id(UUID.fromString(eventEnvelope.getAggregateId())).build();
                    BeanUtils.copyProperties(event, todo);
                    todoRepository.save(todo);
                })
                .build();
    }
}
