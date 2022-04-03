package com.example.demoeventuate.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id) {
        super(String.format("Todo with id=%s not found"));
    }
}
