package com.example.demoeventuate.service;

import com.example.demoeventuate.dto.CreateTodoRequest;
import com.example.demoeventuate.model.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    UUID create(CreateTodoRequest input);

    List<Todo> getAll();
}
