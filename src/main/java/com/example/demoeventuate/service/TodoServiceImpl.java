package com.example.demoeventuate.service;

import com.example.demoeventuate.dto.CreateTodoRequest;
import com.example.demoeventuate.events.TodoCreated;
import com.example.demoeventuate.model.Todo;
import com.example.demoeventuate.repository.TodoRepository;
import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

@Transactional("eventTransactionManager")
@Service
public class TodoServiceImpl implements TodoService {
    private final String AGGREGATE = "TODO";
    private final TodoRepository todoRepository;
    private final DomainEventPublisher domainEventPublisher;

    public TodoServiceImpl(TodoRepository todoRepository, DomainEventPublisher domainEventPublisher) {
        this.todoRepository = todoRepository;
        this.domainEventPublisher = domainEventPublisher;
    }


    @Override
    public UUID create(CreateTodoRequest input) {
        TodoCreated event = TodoCreated.builder()
                .id(UUID.randomUUID())
                .title(input.getTitle())
                .completed(input.isCompleted())
                .executionOrder(input.getOrder())
                .build();
        publishTodoEvent(event);
        return event.getId();
    }

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    private void publishTodoEvent(TodoCreated event) {
        domainEventPublisher.publish(Todo.class, event.getId(), List.of(event));
    }

    private void publishTodoEvent(UUID id, DomainEvent... domainEvents) {
        domainEventPublisher.publish(Todo.class, id, asList(domainEvents));
    }
}
