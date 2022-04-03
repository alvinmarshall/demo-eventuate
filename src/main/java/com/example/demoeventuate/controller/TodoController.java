package com.example.demoeventuate.controller;

import com.example.demoeventuate.dto.CreateTodoRequest;
import com.example.demoeventuate.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody CreateTodoRequest command) {
        return new ResponseEntity<>(todoService.create(command), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(todoService.getAll(), HttpStatus.OK);
    }
}
